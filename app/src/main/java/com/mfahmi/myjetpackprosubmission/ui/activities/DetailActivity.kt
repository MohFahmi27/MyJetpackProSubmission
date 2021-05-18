package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.databinding.ActivityDetailBinding
import com.mfahmi.myjetpackprosubmission.di.Injection.movieInjectRepository
import com.mfahmi.myjetpackprosubmission.di.Injection.tvShowInjectRepository
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.ui.viewmodels.DetailViewModel
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryDetail
import com.mfahmi.myjetpackprosubmission.utils.setDetailGlide
import com.mfahmi.myjetpackprosubmission.utils.setVisibility
import com.shashank.sony.fancytoastlib.FancyToast

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactoryDetail(
            movieInjectRepository(applicationContext), tvShowInjectRepository(applicationContext)
        )
    }

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackDetail.setOnClickListener { finish() }

        binding.pgDetail.setVisibility(true)
        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MoviesFragment::class.java.simpleName)) {
                val movie = intent?.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity
                viewModel.id = movie.movieId
                populateMovieView(movie)
            } else {
                val tvShow = intent?.getParcelableExtra<TvShowEntity>(EXTRA_TV_SHOW) as TvShowEntity
                viewModel.id = tvShow.tvShowId
                populateTvShowView(tvShow)
            }
        }

    }

    private fun populateMovieView(movie: MovieEntity) {
        with(binding) {
            imgPosterDetail.setDetailGlide(movie.posterPath)
            tvTitleDetail.text = movie.title
            tvRatingDetail.text = resources.getString(R.string.rating_format, movie.voteAverage)
            tvReleaseDate.text = movie.releaseDate
            tvOriginalLangDetail.text = movie.originalLanguage
            tvVoteCountDetail.text = movie.voteCount.toString()
            tvOverview.text = movie.overview
            pgDetail.setVisibility(false)

            viewModel.checkBookmarkMovie(movie.movieId).observe(this@DetailActivity) {
                fabBookmark.setOnClickListener {
                    val movieStatus = !movie.isBookmark
                    viewModel.setBookmarkMovie(movie, movieStatus)
                    showFancyToast(movieStatus)
                }
                setFabIcon(it)
            }
        }
    }

    private fun populateTvShowView(tvShow: TvShowEntity) {
        with(binding) {
            imgPosterDetail.setDetailGlide(tvShow.posterPath)
            tvTitleDetail.text = tvShow.name
            tvRatingDetail.text = resources.getString(R.string.rating_format, tvShow.voteAverage)
            tvVoteCountDetail.text = tvShow.voteCount.toString()
            tvReleaseDate.text = tvShow.firstAirDate
            tvOriginalLangDetail.text = tvShow.originalLanguage
            tvOverview.text = tvShow.overview
            pgDetail.setVisibility(false)

            viewModel.checkBookmarkTvShow(tvShow.tvShowId).observe(this@DetailActivity) {
                fabBookmark.setOnClickListener {
                    val tvShowStatus = !tvShow.isBookmark
                    viewModel.setBookmarkTvShow(tvShow, tvShowStatus)
                    showFancyToast(tvShowStatus)
                }
                setFabIcon(it)
            }
        }
    }

    private fun showFancyToast(status: Boolean) {
        if (status) {
            FancyToast.makeText(
                this, getString(R.string.msg_add), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,
                false
            )
        } else {
            FancyToast.makeText(
                this, getString(R.string.msg_remove), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,
                false
            )
        }
    }

    private fun setFabIcon(state: Boolean) {
        with(binding) {
            if (state) fabBookmark.setImageResource(R.drawable.ic_bookmark_filled)
            else fabBookmark.setImageResource(R.drawable.ic_bookmark_outlined)
        }
    }
}