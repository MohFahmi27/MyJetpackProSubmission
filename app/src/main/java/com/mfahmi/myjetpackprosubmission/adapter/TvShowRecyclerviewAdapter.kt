package com.mfahmi.myjetpackprosubmission.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.ItemsDataLayoutBinding
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseTvShow
import com.mfahmi.myjetpackprosubmission.ui.activities.DetailActivity
import com.mfahmi.myjetpackprosubmission.ui.fragments.TvShowFragment
import com.mfahmi.myjetpackprosubmission.utils.CustomOnItemClickListener
import com.mfahmi.myjetpackprosubmission.utils.setAnimationRecyclerView
import com.mfahmi.myjetpackprosubmission.utils.setRoundedGlide

class TvShowRecyclerviewAdapter(private var listItems: List<ResponseTvShow>) :
    RecyclerView.Adapter<TvShowRecyclerviewAdapter.TvShowViewHolder>() {

    inner class TvShowViewHolder(private val binding: ItemsDataLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: ResponseTvShow) {
            with(binding) {
                imgPoster.setRoundedGlide(tvShows.posterPath)
                tvTitle.text = tvShows.name
                tvRating.text = tvShows.voteAverage.toString()
                tvReleaseDate.text = tvShows.firstAirDate
                tvOverviewItems.text = itemView.context.getString(
                    R.string.overview_format,
                    tvShows.overview.substring(0, 25)
                )
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    adapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java).apply {
                                putExtra(DetailActivity.EXTRA_DETAIL_ID, tvShows.id)
                                putExtra(
                                    DetailActivity.EXTRA_TYPE,
                                    TvShowFragment::class.java.simpleName
                                )
                                itemView.context.startActivity(this)
                            }
                        }
                    })
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            ItemsDataLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listItems[position])
        holder.itemView.setAnimationRecyclerView()
    }

    override fun getItemCount(): Int = listItems.size
}