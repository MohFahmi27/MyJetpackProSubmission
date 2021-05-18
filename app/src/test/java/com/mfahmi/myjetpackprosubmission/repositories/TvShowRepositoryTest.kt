package com.mfahmi.myjetpackprosubmission.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.data.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
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
class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepositories = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val tvShowRepository = FakeTvShowRepository(remoteRepositories, localDataSource, appExecutors)

    private val tvShowData = DataDummy.getPopularTvShowDummy()
    private val idTestTvShow = 791373

    @Test
    fun getTvShowsData() {
        val myDataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(localDataSource.getTvShowFromDb()).thenReturn(myDataSourceFactory)
        tvShowRepository.getTvShowsData()

        val tvShowResult =
            ResourceValue.success(PagedListUtil.mockPagedList(DataDummy.getPopularTvShowDummy()))
        verify(localDataSource).getTvShowFromDb()
        assertNotNull(tvShowResult.data)
        assertEquals(tvShowData.size, tvShowResult.data?.size)
    }

    @Test
    fun checkBookmarkTvShow() {
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = tvShowData[0].isBookmark
        `when`(localDataSource.checkTvShowById(idTestTvShow)).thenReturn(stateOnDB)

        val stateOnRepo =
            LiveDataTestUtil.getValue(tvShowRepository.checkBookmarkTvShow(idTestTvShow))
        verify(localDataSource).checkTvShowById(idTestTvShow)
        assertFalse(stateOnRepo)
        assertEquals(stateOnDB.value, stateOnRepo)
    }

    @Test
    fun getBookmarkDataTvShow() {
        val myDataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        val bookmarkedTvShow = DataDummy.generateBookmarkTvShowData()
        `when`(localDataSource.getBookmarkTvShowData()).thenReturn(myDataSourceFactory)
        tvShowRepository.getBookmarkDataTvShow()

        val tvShowResult = PagedListUtil.mockPagedList(bookmarkedTvShow)
        verify(localDataSource).getBookmarkTvShowData()
        assertNotNull(tvShowResult)
        assertEquals(1, tvShowResult.size)
    }
}