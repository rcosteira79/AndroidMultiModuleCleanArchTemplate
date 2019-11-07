package com.rcosteira.rxjavatocoroutines.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.rcosteira.core.data.GithubUsersRepository
import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.data.api.MockWebServerSetup
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.cache.GithubDatabase
import com.rcosteira.core.data.cache.RoomCache
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.rxjavatocoroutines.domain.usecases.*
import com.rcosteira.rxjavatocoroutines.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RxJavaToCoroutinesViewModelTest {
    private lateinit var webServer: MockWebServer
    private lateinit var database: GithubDatabase
    private lateinit var viewModel: RxJavaToCoroutinesViewModel
    private lateinit var compositeDisposable: CompositeDisposable

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
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
            compositeDisposable,
            mainCoroutineRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        webServer.shutdown()
    }

//    @Test
//    fun getUsersFromApiAsSingle_successful_returnsASingleListOfUsers() {
//        val expectedNumberOfUsers = 2
//        val expectedFirstUserLocation = "San Francisco"
//
//        val testObserver: TestObserver<List<DetailedUser>> = viewModel.getUsersFromApiAsSingle().test()
//
//        testObserver.assertComplete()
//        testObserver.assertNoErrors()
//
//        val users: List<DetailedUser> = testObserver.values().first() // List<List<DetailedUser>>
//        assertThat(users.count()).isEqualTo(expectedNumberOfUsers)
//        assertThat(users.first().location.value).isEqualTo(expectedFirstUserLocation)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun updateCacheWithRx_successful() {
//        val expectedNumberOfUsers = 2
//        val expectedSecondUserBlog = "http://chriswanstrath.com/"
//
//        viewModel.updateCacheWithRx()
//
//        val testSubscriber: TestSubscriber<List<GithubDetailedUser>> = database.usersDao().rxGetAllUsers().test()
//        val users: List<GithubDetailedUser> = testSubscriber.values().first() // List<List<GithubDetailedUser>>
//
//        assertThat(users.count()).isEqualTo(expectedNumberOfUsers)
//        assertThat(users.last().blog).isEqualTo(expectedSecondUserBlog)
//    }
//
//    @Test
//    fun setUserViewStateFromCacheWithRx_successful() {
//        viewModel.updateCacheWithRx()
//
//        viewModel.setUserViewStateFromCacheWithRx()
//
//        val users = listOf(
//            DisplayedDetailedUser(
//                Id(1),
//                Username("mojombo"),
//                Name("Tom Preston-Werner"),
//                Blog("http://tom.preston-werner.com"),
//                Location("San Francisco"),
//                Avatar("https://avatars0.githubusercontent.com/u/1?v=4")
//            ),
//            DisplayedDetailedUser(
//                Id(2),
//                Username("defunkt"),
//                Name("Chris Wanstrath"),
//                Blog("http://chriswanstrath.com/"),
//                Location("N/A"),
//                Avatar("https://avatars0.githubusercontent.com/u/2?v=4")
//            )
//        )
//
//        assertThat(viewModel.viewState.value).isEqualTo(RxJavaToCoroutinesViewState(detailedUsers = users))
//    }

    //****************************** coroutines **********************************/
    @ExperimentalCoroutinesApi
    @Test
    fun getUsersFromApiThroughCoroutine_successful() = runBlocking {
        val expectedNumberOfUsers = 2
        val expectedFirstUserLocation = "San Francisco"

        val users = viewModel.getUsersFromApiThroughCoroutine(this)

        assertThat(users.count()).isEqualTo(expectedNumberOfUsers)
        assertThat(users.first().location.value).isEqualTo(expectedFirstUserLocation)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateCacheWithCoroutines_successful() = mainCoroutineRule.runBlockingTest {
        val expectedNumberOfUsers = 2
        val expectedSecondUserBlog = "http://chriswanstrath.com/"

        viewModel.updateCacheWithCoroutines()

        // it's an infinite stream, and Flow does not seem to have specific test utilities for these cases yet
        withTimeoutOrNull(2000) {
            database.usersDao().getAllUsers()
                .collect { users ->
                    assertThat(users.count()).isEqualTo(expectedNumberOfUsers)
                    assertThat(users.last().blog).isEqualTo(expectedSecondUserBlog)
                }
        }
    }
}