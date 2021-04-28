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
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryTvShows
import com.mfahmi.myjetpackprosubmission.viewmodels.TvShowViewModel

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private val binding: FragmentTvShowBinding by viewBinding()
    private val viewModel: TvShowViewModel by activityViewModels {
        ViewModelFactoryTvShows(Injection.tvShowInjectRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgressVisibility(true)
        viewModel.getTvShows().observe(requireActivity()) {
            binding.rvTvShows.layoutManager = LinearLayoutManager(requireContext())
            binding.rvTvShows.adapter = TvShowRecyclerviewAdapter(it)
            setProgressVisibility(false)
        }
    }

    private fun setProgressVisibility(state: Boolean) {
        if (state) binding.pgTvShow.visibility = View.VISIBLE else binding.pgTvShow.visibility = View.GONE
    }

}