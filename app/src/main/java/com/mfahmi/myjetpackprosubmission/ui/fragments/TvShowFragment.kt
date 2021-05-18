package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.FragmentTvShowBinding
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.ui.adapter.TvShowRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.ui.viewmodels.TvShowViewModel
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryTvShows
import com.mfahmi.myjetpackprosubmission.utils.setVisibility
import com.mfahmi.myjetpackprosubmission.vo.Status
import com.shashank.sony.fancytoastlib.FancyToast

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private val binding: FragmentTvShowBinding by viewBinding()
    private val viewModel: TvShowViewModel by activityViewModels {
        ViewModelFactoryTvShows(Injection.tvShowInjectRepository(requireContext()))
    }
    private val tvShowRecyclerviewAdapter by lazy {
        TvShowRecyclerviewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTvShows.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTvShows.adapter = tvShowRecyclerviewAdapter

        binding.pgTvShow.setVisibility(true)
        viewModel.getTvShows().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding.pgTvShow.setVisibility(true)
                Status.SUCCESS -> {
                    binding.pgTvShow.setVisibility(false)
                    tvShowRecyclerviewAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    binding.pgTvShow.setVisibility(true)
                    FancyToast.makeText(
                        context,
                        getString(R.string.something_when_wrong),
                        FancyToast.LENGTH_SHORT, FancyToast.WARNING, false
                    ).show()
                }
            }
        }
    }

}