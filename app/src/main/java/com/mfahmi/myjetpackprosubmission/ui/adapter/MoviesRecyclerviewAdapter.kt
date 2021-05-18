package com.mfahmi.myjetpackprosubmission.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.databinding.ItemsDataLayoutBinding
import com.mfahmi.myjetpackprosubmission.ui.activities.DetailActivity
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.utils.CustomOnItemClickListener
import com.mfahmi.myjetpackprosubmission.utils.setAnimationRecyclerView
import com.mfahmi.myjetpackprosubmission.utils.setRoundedGlide

class MoviesRecyclerviewAdapter :
    PagedListAdapter<MovieEntity, MoviesRecyclerviewAdapter.MainRecyclerviewViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK by lazy {
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

            }
        }
    }

    inner class MainRecyclerviewViewHolder(private val binding: ItemsDataLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieEntity) {
            with(binding) {
                imgPoster.setRoundedGlide(movies.posterPath)
                tvTitle.text = movies.title
                tvRating.text = movies.voteAverage.toString()
                tvReleaseDate.text = movies.releaseDate
                tvOverviewItems.text = itemView.context.getString(
                    R.string.overview_format,
                    movies.overview.substring(0, 25)
                )
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java).apply {
                                putExtra(DetailActivity.EXTRA_MOVIE, movies)
                                putExtra(
                                    DetailActivity.EXTRA_TYPE,
                                    MoviesFragment::class.java.simpleName
                                )
                                itemView.context.startActivity(this)
                            }
                        }
                    })
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerviewViewHolder {
        return MainRecyclerviewViewHolder(
            ItemsDataLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainRecyclerviewViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
        holder.itemView.setAnimationRecyclerView()
    }
}