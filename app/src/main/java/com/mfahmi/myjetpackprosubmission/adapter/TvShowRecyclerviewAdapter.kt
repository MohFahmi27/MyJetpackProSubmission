package com.mfahmi.myjetpackprosubmission.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.myjetpackprosubmission.databinding.ItemsDataLayoutBinding
import com.mfahmi.myjetpackprosubmission.models.TvShowEntity
import com.mfahmi.myjetpackprosubmission.ui.activities.DetailActivity
import com.mfahmi.myjetpackprosubmission.ui.fragments.TvShowFragment
import com.mfahmi.myjetpackprosubmission.utils.CustomOnItemClickListener
import com.mfahmi.myjetpackprosubmission.utils.ViewUtils

class TvShowRecyclerviewAdapter(
    private val context: Context,
    private var listItems: ArrayList<TvShowEntity>
) :
    RecyclerView.Adapter<TvShowRecyclerviewAdapter.TvShowViewHolder>() {

    inner class TvShowViewHolder(private val binding: ItemsDataLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShowEntity) {
            with(binding) {
                ViewUtils.setGlide(context, tvShows.tvShowPosterPath, imgPoster)
                tvTitle.text = tvShows.tvShowTitle
                tvRating.text = tvShows.tvShowRating.toString()
                tvReleaseDate.text = tvShows.tvShowReleaseDate
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    adapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                    .apply { putExtra(DetailActivity.EXTRA_DETAIL_ID, tvShows.tvShowId) }
                                    .apply { putExtra(DetailActivity.EXTRA_TYPE, TvShowFragment::class.java.simpleName) }
                                    .run { itemView.context.startActivity(this) }
                        }
                    })
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            ItemsDataLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listItems[position])
        ViewUtils.setAnimation(context, holder.itemView)
    }

    override fun getItemCount(): Int = listItems.size
}