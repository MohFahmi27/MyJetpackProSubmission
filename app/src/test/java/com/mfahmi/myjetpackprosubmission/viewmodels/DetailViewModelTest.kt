package com.mfahmi.myjetpackprosubmission.viewmodels

import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.system.measureNanoTime

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val idTestMovie = 412656
    private val idTestTvShow = 63174

    @Before
    fun init() {
        viewModel = DetailViewModel()
    }

    @Test
    fun benchMarkUsingLambdaOrForLoop() {
        // test using the worst case(last element of array)
        val timeForLoop = measureNanoTime { findMovieByIdUsingForLoop(664767) }
        val timeLambda = measureNanoTime { findMovieByLambda(664767) }
        println("For Loop: $timeForLoop, Lambda find{}: $timeLambda")
        assertTrue(timeForLoop > timeLambda)
    }

    private fun findMovieByLambda(idMovie: Int): MoviesEntity? {
        return MoviesRepository.getMoviesData().find { it.movieId == idMovie }
    }

    private fun findMovieByIdUsingForLoop(idMovie: Int): MoviesEntity {
        lateinit var movie: MoviesEntity
        for (i in MoviesRepository.getMoviesData()) {
            if (i.movieId == idMovie) {
                movie = i
            }
        }
        return movie
    }

    @Test
    fun validateDataMovieWithId() {
        viewModel.id = idTestMovie
        val getMovieDetailData = viewModel.getMovieDetail()
        assertNotNull(getMovieDetailData)
        assertEquals(MoviesRepository.getMoviesData()[2], getMovieDetailData)
    }

    @Test
    fun validateDataTvShowWithId() {
        viewModel.id = idTestTvShow
        val getTvShowDetailData = viewModel.getTvShowDetail()
        assertNotNull(getTvShowDetailData)
        assertEquals(TvShowRepository.getTvShowData().last(), getTvShowDetailData)
    }

    @Test
    fun invalidIdMovie() {
        viewModel.id = 0
        assertNull(viewModel.getMovieDetail())
    }

    @Test
    fun invalidIdTvShow() {
        viewModel.id = 0
        assertNull(viewModel.getTvShowDetail())
    }

}