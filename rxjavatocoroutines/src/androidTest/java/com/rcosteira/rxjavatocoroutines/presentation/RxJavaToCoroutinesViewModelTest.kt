package com.rcosteira.rxjavatocoroutines.presentation

import RxImmediateSchedulerRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.rcosteira.core.data.GithubUsersRepository
import com.rcosteira.core.data.MockWebServerSetup
import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.cache.GithubDatabase
import com.rcosteira.core.data.cache.RoomCache
import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.rxjavatocoroutines.domain.usecases.*
import com.rcosteira.rxjavatocoroutines.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RxJavaToCoroutinesViewModelTest {
    private lateinit var webServer: MockWebServer
    private lateinit var database: GithubDatabase
    private lateinit var viewModel: RxJavaToCoroutinesViewModel
    private lateinit var compositeDisposable: CompositeDisposable

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, GithubDatabase::class.java).build()
        val cache: Cache = RoomCache(database.usersDao())
        webServer = MockWebServerSetup.getMockWebServer(context)
        // In a real life example, this would be provided by Dagger
        val retrofit = Retrofit.Builder()
            .baseUrl(webServer.url("/"))
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val api: Api = GithubApi(retrofit)
        val usersRepository = GithubUsersRepository(api, cache, UserMapper(), DetailedUserMapper())

        compositeDisposable = CompositeDisposable()

        viewModel = RxJavaToCoroutinesViewModel(
            GetUsersFromApi(usersRepository),
            GetUserDetailsFromApi(usersRepository),
            GetCachedUsers(usersRepository),
            RxGetUsersFromApi(usersRepository),
            RxGetUserDetailsFromApi(usersRepository),
            RxGetCachedUsers(usersRepository),
            UpdateCachedUsers(usersRepository),
            RxDeleteCachedUser(usersRepository),
            DisplayedDetailedUserMapper(),
            compositeDisposable
        )
    }

    @After
    fun teardown() {
        webServer.shutdown()
    }

    @Test
    fun getUsersFromApiAsSingle_successful_returnsASingleListOfUsers() {
        val testObserver: TestObserver<List<DetailedUser>> = viewModel.getUsersFromApiAsSingle().test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val users: List<DetailedUser> = testObserver.values().first() // List<List<DetailedUser>>
        assertThat(users.count()).isEqualTo(2)
        assertThat(users.first().location.value).isEqualTo("San Francisco")
    }

    @Test
    fun updateCacheWithRx_successful() {
        viewModel.updateCacheWithRx()
        val testSubscriber: TestSubscriber<List<GithubDetailedUser>> = database.usersDao().rxGetAllUsers().test()
        val users: List<GithubDetailedUser> = testSubscriber.values().first() // List<List<GithubDetailedUser>>

        assertThat(users.count()).isEqualTo(2)
        assertThat(users.last().blog).isEqualTo("http://chriswanstrath.com/")
    }
}