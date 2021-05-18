package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.FragmentTvShowBookmarkBinding
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.ui.adapter.TvShowRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.ui.viewmodels.BookmarkViewModel
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryBookmark
import com.mfahmi.myjetpackprosubmission.utils.setVisibility

class TvShowBookmarkFragment : Fragment(R.layout.fragment_tv_show_bookmark) {
    private val binding: FragmentTvShowBookmarkBinding by viewBinding()
    private val viewModel: BookmarkViewModel by activityViewModels {
        ViewModelFactoryBookmark(
            Injection.movieInjectRepository(requireContext()),
            Injection.tvShowInjectRepository(requireContext())
        )
    }
    private val adapter by lazy { TvShowRecyclerviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pgTvShowFavorite.setVisibility(true)
            rvTvShowsFavorite.adapter = adapter
            rvTvShowsFavorite.layoutManager = LinearLayoutManager(requireContext())

            viewModel.getBookmarkTvShow().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                pgTvShowFavorite.setVisibility(false)
            }
        }
    }
}