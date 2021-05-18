package com.mfahmi.myjetpackprosubmission.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.data.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.utils.AppExecutors
import com.mfahmi.myjetpackprosubmission.utils.LiveDataTestUtil
import com.mfahmi.myjetpackprosubmission.utils.PagedListUtil
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMoviesRepository(remoteDataSource, localDataSource, appExecutors)

    private val movieData = DataDummy.getPopularMovieDummy()
    private val idTestMovie = 399566

    @Test
    fun getMoviesData() {
        val myDataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getMovieFromDb()).thenReturn(myDataSourceFactory)
        movieRepository.getMoviesData()

        val movieResult = ResourceValue.success(PagedListUtil.mockPagedList(DataDummy.getPopularMovieDummy()))
        verify(localDataSource).getMovieFromDb()
        assertNotNull(movieResult.data)
        assertEquals(movieData.size, movieResult.data?.size)
    }

    @Test
    fun checkBookmarkMovie() {
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = movieData[0].isBookmark
        `when`(localDataSource.checkMovieById(idTestMovie)).thenReturn(stateOnDB)

        val stateOnRepo = LiveDataTestUtil.getValue(movieRepository.checkBookmarkMovie(idTestMovie))

        verify(localDataSource).checkMovieById(idTestMovie)
        assertTrue(stateOnRepo)
        assertEquals(stateOnDB.value, stateOnRepo)
    }

    @Test
    fun getBookmarkDataTvShow() {
        val myDataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        val bookmarkedMovie = DataDummy.generateBookmarkMovieData()
        `when`(localDataSource.getBookmarkMovieData()).thenReturn(myDataSourceFactory)
        movieRepository.getBookmarkDataMovie()

        val movieResult = PagedListUtil.mockPagedList(bookmarkedMovie)
        verify(localDataSource).getBookmarkMovieData()
        assertNotNull(movieResult)
        assertEquals(2, movieResult.size)
    }

}