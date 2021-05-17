package com.mfahmi.myjetpackprosubmission.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailMovieCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetMoviesCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.utils.LiveDataTestUtil
import com.mfahmi.myjetpackprosubmission.utils.TestHelper.anyOfT
import com.mfahmi.myjetpackprosubmission.utils.TestHelper.eqOfT
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepositories = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMoviesRepository(remoteRepositories)

    private val movieData = DataDummy.getPopularMovieDummy()
    private val idTestMovie = 399566

    @Test
    fun getMoviesData() {
        Mockito.doAnswer {
            (it.arguments[0] as GetMoviesCallback).onResponse(movieData)
        }.`when`(remoteRepositories).getPopularMovies(anyOfT(GetMoviesCallback::class.java))

        val movieResult = LiveDataTestUtil.getValue(movieRepository.getMoviesData())
        verify(remoteRepositories).getPopularMovies(anyOfT(GetMoviesCallback::class.java))
        assertNotNull(movieResult)
        assertEquals(movieData.size, movieResult.size)
    }

    @Test
    fun getDetailMoviesData() {
        Mockito.doAnswer {
            (it.arguments[1] as GetDetailMovieCallback)
                    .onResponse(DataDummy.getDetailMovie(idTestMovie))
        }.`when`(remoteRepositories).getDetailMovie(eqOfT(idTestMovie), anyOfT(GetDetailMovieCallback::class.java))

        val movieDetailResult = LiveDataTestUtil.getValue(movieRepository.getDetailMoviesData(idTestMovie))
        verify(remoteRepositories).getDetailMovie(eqOfT(idTestMovie), anyOfT(GetDetailMovieCallback::class.java))
        assertNotNull(movieDetailResult)
        assertEquals(movieDetailResult, DataDummy.getDetailMovie(idTestMovie))
    }
}