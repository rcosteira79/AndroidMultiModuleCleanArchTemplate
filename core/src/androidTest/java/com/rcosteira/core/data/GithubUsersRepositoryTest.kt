package com.rcosteira.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.data.api.MockWebServerSetup
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.cache.GithubDatabase
import com.rcosteira.core.data.cache.RoomCache
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubUsersRepositoryTest {
    private lateinit var webServer: MockWebServer
    private lateinit var usersRepository: UsersRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val database = Room.inMemoryDatabaseBuilder(context, GithubDatabase::class.java).build()

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

        usersRepository = GithubUsersRepository(api, cache, UserMapper(), DetailedUserMapper())
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun rxGetUsersFromApi_successful_returnsObservableOfUsers() {
        val testObserver: TestObserver<User> = usersRepository.rxGetUsersFromApi().test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val users: List<User> = testObserver.values()
        assertThat(users.count()).isEqualTo(2)
        assertThat(users.first().username.value).isEqualTo("mojombo")
    }

    @Test
    fun rxGetUserDetailsFromApi_successful_returnsMaybeDetailedUser() {
        val testObserver: TestObserver<DetailedUser> =
            usersRepository.rxGetUserDetailsFromApi(Username("mojombo")).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val users: List<DetailedUser> =
            testObserver.values() // values() returns the list of all events, even it there's just one

        assertThat(users.count()).isEqualTo(1)
        assertThat(users.first().location.value).isEqualTo("San Francisco")
    }

    //****************************** coroutines **********************************/
    @ExperimentalCoroutinesApi
    @Test
    fun getUsersFromApi_successful_returnsListOfUsers() = runBlocking {
        val users = usersRepository.getUsersFromApi()

        assertThat(users.count()).isEqualTo(2)
        assertThat(users.first().username.value).isEqualTo("mojombo")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUserDetailsFromApi_successful_returnsMaybeDetailedUser() = runBlocking {
        val detailedUser = usersRepository.getUserDetailsFromApi(Username("mojombo"))

        assertThat(detailedUser.location.value).isEqualTo("San Francisco")
    }
}