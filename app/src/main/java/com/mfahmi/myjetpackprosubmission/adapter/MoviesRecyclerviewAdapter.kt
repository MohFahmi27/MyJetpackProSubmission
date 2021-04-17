package com.mfahmi.myjetpackprosubmission.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.myjetpackprosubmission.databinding.ItemsDataLayoutBinding
import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.ui.activities.DetailActivity
import com.mfahmi.myjetpackprosubmission.ui.fragments.MoviesFragment
import com.mfahmi.myjetpackprosubmission.utils.CustomOnItemClickListener
import com.mfahmi.myjetpackprosubmission.utils.ViewUtils

class MoviesRecyclerviewAdapter(private val context: Context, private val listItems: ArrayList<MoviesEntity>) :
        RecyclerView.Adapter<MoviesRecyclerviewAdapter.MainRecyclerviewViewHolder>() {

    inner class MainRecyclerviewViewHolder(private val binding: ItemsDataLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                ViewUtils.setGlide(context, movies.moviePosterPath, imgPoster)
                tvTitle.text = movies.movieTitle
                tvRating.text = movies.movieRating.toString()
                tvReleaseDate.text = movies.movieReleaseDate
            }
            itemView.setOnClickListener(
                    CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                    .apply { putExtra(DetailActivity.EXTRA_DETAIL_ID, movies.movieId) }
                                    .apply { putExtra(DetailActivity.EXTRA_TYPE, MoviesFragment::class.java.simpleName) }
                                    .run { itemView.context.startActivity(this) }
                        }
                    })
            )
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainRecyclerviewViewHolder {
        return MainRecyclerviewViewHolder(
                ItemsDataLayoutBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainRecyclerviewViewHolder, position: Int) {
        holder.bind(listItems[position])
        ViewUtils.setAnimation(context, holder.itemView)
    }

    override fun getItemCount(): Int = listItems.size
}