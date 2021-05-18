package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.databinding.FragmentMoviesBinding
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.ui.adapter.MoviesRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.ui.viewmodels.MovieViewModel
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryMovie
import com.mfahmi.myjetpackprosubmission.utils.setVisibility
import com.mfahmi.myjetpackprosubmission.vo.Status.*
import com.shashank.sony.fancytoastlib.FancyToast

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding: FragmentMoviesBinding by viewBinding()
    private val viewModel: MovieViewModel by activityViewModels {
        ViewModelFactoryMovie(Injection.movieInjectRepository(requireContext()))
    }
    private val moviesRecyclerviewAdapter by lazy { MoviesRecyclerviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvMovies.layoutManager = LinearLayoutManager(requireContext())
            rvMovies.adapter = moviesRecyclerviewAdapter

            viewModel.getMovies().observe(viewLifecycleOwner) {
                when (it.status) {
                    LOADING -> pgMovie.setVisibility(true)
                    SUCCESS -> {
                        pgMovie.setVisibility(false)
                        moviesRecyclerviewAdapter.submitList(it.data)
                    }
                    ERROR -> {
                        pgMovie.setVisibility(true)
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

}