package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.adapter.TvShowRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.databinding.FragmentTvShowBinding
import com.mfahmi.myjetpackprosubmission.viewmodels.TvShowViewModel

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private val binding: FragmentTvShowBinding by viewBinding()
    private val viewModel: TvShowViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTvShows.layoutManager = LinearLayoutManager(context)
        binding.rvTvShows.adapter =
            TvShowRecyclerviewAdapter(requireContext(), viewModel.getTvShows())
    }

}