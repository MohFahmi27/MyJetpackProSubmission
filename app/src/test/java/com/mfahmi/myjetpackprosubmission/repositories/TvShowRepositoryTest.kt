package com.mfahmi.myjetpackprosubmission.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailTvShowCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetTvShowsCallback
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

class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepositories = Mockito.mock(RemoteDataSource::class.java)
    private val tvShowRepository = FakeTvShowRepository(remoteRepositories)

    private val tvShowData = DataDummy.getPopularTvShowDummy()
    private val idTestTvShow = 791373

    @Test
    fun getTvShowsData() {
        Mockito.doAnswer {
            (it.arguments[0] as GetTvShowsCallback).onResponse(tvShowData)
        }.`when`(remoteRepositories).getPopularTvShows(anyOfT(GetTvShowsCallback::class.java))

        val tvShowResult = LiveDataTestUtil.getValue(tvShowRepository.getTvShowsData())
        verify(remoteRepositories).getPopularTvShows(anyOfT(GetTvShowsCallback::class.java))
        assertNotNull(tvShowResult)
        assertEquals(tvShowData.size, tvShowResult.size)
        assertEquals(tvShowData[0].id, tvShowResult[0].id)
    }

    @Test
    fun getTvShowDetailData() {
        Mockito.doAnswer {
            (it.arguments[1] as GetDetailTvShowCallback)
                    .onResponse(DataDummy.getDetailTvShow(idTestTvShow))
        }.`when`(remoteRepositories).getDetailTvShow(eqOfT(idTestTvShow), anyOfT(GetDetailTvShowCallback::class.java))

        val tvShowDetailResult = LiveDataTestUtil.getValue(tvShowRepository.getTvShowDetailData(idTestTvShow))
        verify(remoteRepositories).getDetailTvShow(eqOfT(idTestTvShow), anyOfT(GetDetailTvShowCallback::class.java))
        assertNotNull(tvShowDetailResult)
        assertEquals(tvShowDetailResult, DataDummy.getDetailTvShow(idTestTvShow))
        assertEquals(tvShowDetailResult.id, tvShowData[1].id)
    }
}