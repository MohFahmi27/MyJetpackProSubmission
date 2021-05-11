package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ActivityDetailBinding
import com.mfahmi.myjetpackprosubmission.di.Injection.movieInjectRepository
import com.mfahmi.myjetpackprosubmission.di.Injection.tvShowInjectRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseTvShow
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryDetail
import com.mfahmi.myjetpackprosubmission.utils.setDetailGlide
import com.mfahmi.myjetpackprosubmission.utils.setVisibility
import com.mfahmi.myjetpackprosubmission.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactoryDetail(application, movieInjectRepository(), tvShowInjectRepository())
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
                val movie = intent?.getParcelableExtra<ResponseMovie>(EXTRA_MOVIE) as ResponseMovie
                viewModel.id = movie.id
                populateMovieView()
            } else {
                val tvShow = intent?.getParcelableExtra<ResponseTvShow>(EXTRA_TV_SHOW) as ResponseTvShow
                viewModel.id = tvShow.id
                populateTvShowView()
            }
        }

    }

    private fun populateMovieView() {
        viewModel.getMovieDetail().observe(this) {
            with(binding) {
                imgPosterDetail.setDetailGlide(it.posterPath)
                tvTitleDetail.text = it.title
                tvRatingDetail.text = resources.getString(R.string.rating_format, it.voteAverage)
                tvReleaseDate.text = it.releaseDate
                tvTagLine.text = it.tagline
                tvStatusDetail.text = it.status
                tvDuration.text = resources.getString(R.string.duration_format_movie, it.runtime)
                tvOverview.text = it.overview
                pgDetail.setVisibility(false)
            }
        }
    }

    private fun populateTvShowView() {
        viewModel.getTvShowDetail().observe(this) {
            with(binding) {
                imgPosterDetail.setDetailGlide(it.posterPath)
                tvTitleDetail.text = it.name
                tvRatingDetail.text = resources.getString(R.string.rating_format, it.voteAverage)
                tvReleaseDate.text = it.firstAirDate
                tvTagLine.text = it.tagline
                tvStatusDetail.text = it.status
                tvDuration.text =
                    resources.getString(R.string.duration_format_tv_show, it.numberOfEpisodes)
                tvOverview.text = it.overview
                pgDetail.setVisibility(false)
            }
        }
    }
}