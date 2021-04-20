package com.mfahmi.myjetpackprosubmission.adapter

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
import com.mfahmi.myjetpackprosubmission.utils.setAnimationRecyclerView
import com.mfahmi.myjetpackprosubmission.utils.setGlide

class MoviesRecyclerviewAdapter(private val listItems: ArrayList<MoviesEntity>) :
        RecyclerView.Adapter<MoviesRecyclerviewAdapter.MainRecyclerviewViewHolder>() {

    inner class MainRecyclerviewViewHolder(private val binding: ItemsDataLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                imgPoster.setGlide(movies.moviePosterPath)
                tvTitle.text = movies.movieTitle
                tvRating.text = movies.movieRating.toString()
                tvReleaseDate.text = movies.movieReleaseDate
            }
            itemView.setOnClickListener(
                    CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                    .apply {
                                        putExtra(DetailActivity.EXTRA_DETAIL_ID, movies.movieId)
                                        putExtra(DetailActivity.EXTRA_TYPE, MoviesFragment::class.java.simpleName)
                                        itemView.context.startActivity(this)
                                    }
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
                ItemsDataLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainRecyclerviewViewHolder, position: Int) {
        holder.bind(listItems[position])
        holder.itemView.setAnimationRecyclerView()
    }

    override fun getItemCount(): Int = listItems.size
}