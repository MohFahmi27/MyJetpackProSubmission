package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ActivityDetailBinding
import com.mfahmi.myjetpackprosubmission.di.Injection.movieInjectRepository
import com.mfahmi.myjetpackprosubmission.di.Injection.tvShowInjectRepository
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryDetail
import com.mfahmi.myjetpackprosubmission.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactoryDetail(movieInjectRepository(), tvShowInjectRepository())
    }

    companion object {
        const val EXTRA_DETAIL_ID = "extra_detail_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackDetail.setOnClickListener { finish() }

        setProgressVisibility(true)
        viewModel.id = intent?.getIntExtra(EXTRA_DETAIL_ID, 0) as Int
        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MoviesFragment::class.java.simpleName)) {
                populateMovieView()
            } else {
                populateTvShowView()
            }
        }

    }

    private fun populateMovieView() {
        viewModel.getMovieDetail().observe(this) {
            with(binding) {
                Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                        .apply(RequestOptions().transform(RoundedCorners(15)))
                        .into(imgPosterDetail)
                tvTitleDetail.text = it.title
                tvRatingDetail.text = it.voteAverage.toString()
                tvReleaseDate.text = it.releaseDate
                tvTagLine.text = it.tagline
                tvStatusDetail.text = resources.getString(R.string.status_format, it.status)
                tvDuration.text = resources.getString(R.string.duration_format_movie, it.runtime)
                tvOverview.text = it.overview
                setProgressVisibility(false)
            }
        }
    }

    private fun populateTvShowView() {
        viewModel.getTvShowDetail().observe(this) {
            with(binding) {
                Glide.with(this@DetailActivity).load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                        .apply(RequestOptions().transform(RoundedCorners(15)))
                        .into(imgPosterDetail)
                tvTitleDetail.text = it.name
                tvRatingDetail.text = it.voteAverage.toString()
                tvReleaseDate.text = it.firstAirDate
                tvTagLine.text = it.tagline
                tvStatusDetail.text = resources.getString(R.string.status_format, it.status)
                tvDuration.text = resources.getString(R.string.duration_format_tv_show, it.numberOfEpisodes)
                tvOverview.text = it.overview
                setProgressVisibility(false)
            }
        }
    }

    private fun setProgressVisibility(state: Boolean) {
        if (state) binding.pgDetail.visibility = View.VISIBLE else binding.pgDetail.visibility = View.GONE
    }

}