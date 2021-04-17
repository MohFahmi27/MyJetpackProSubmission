package com.mfahmi.myjetpackprosubmission.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mfahmi.myjetpackprosubmission.databinding.ActivityDetailBinding
import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.models.TvShowEntity
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val EXTRA_DETAIL_ID = "extra_detail_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackDetail.setOnClickListener { finish() }

        viewModel.id = intent?.getIntExtra(EXTRA_DETAIL_ID, 0) as Int
        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MoviesFragment::class.java.simpleName)) {
                viewModel.getMovieDetail()?.let { populateMovieView(it) }
            } else {
                viewModel.getTvShowDetail()?.let { populateTvShowView(it) }
            }
        }

    }

    private fun populateMovieView(movieEntity: MoviesEntity) {
        with(binding) {
            tvToolbarName.text = movieEntity.movieTitle
            Glide.with(this@DetailActivity).load(movieEntity.moviePosterPath)
                    .apply(RequestOptions().transform(RoundedCorners(15)))
                    .into(imgPosterDetail)
            tvTitleDetail.text = movieEntity.movieTitle
            tvRatingDetail.text = movieEntity.movieRating.toString()
            tvReleaseDate.text = movieEntity.movieReleaseDate
            tvGenre.text = movieEntity.movieGenres.toString()
            tvOverview.text = movieEntity.movieOverview
        }
    }

    private fun populateTvShowView(tvShowEntity: TvShowEntity) {
        with(binding) {
            tvToolbarName.text = tvShowEntity.tvShowTitle
            Glide.with(this@DetailActivity).load(tvShowEntity.tvShowPosterPath)
                    .apply(RequestOptions().transform(RoundedCorners(15)))
                    .into(imgPosterDetail)
            tvTitleDetail.text = tvShowEntity.tvShowTitle
            tvRatingDetail.text = tvShowEntity.tvShowRating.toString()
            tvReleaseDate.text = tvShowEntity.tvShowReleaseDate
            tvGenre.text = tvShowEntity.tvShowGenres.toString()
            tvOverview.text = tvShowEntity.tvShowOverview
        }
    }

}