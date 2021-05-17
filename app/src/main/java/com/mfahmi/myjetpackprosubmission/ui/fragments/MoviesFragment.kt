package com.mfahmi.myjetpackprosubmission.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.adapter.MoviesRecyclerviewAdapter
import com.mfahmi.myjetpackprosubmission.databinding.FragmentMoviesBinding
import com.mfahmi.myjetpackprosubmission.di.Injection
import com.mfahmi.myjetpackprosubmission.utils.ViewModelFactoryMovie
import com.mfahmi.myjetpackprosubmission.utils.setVisibility
import com.mfahmi.myjetpackprosubmission.viewmodels.MovieViewModel
import com.mfahmi.myjetpackprosubmission.vo.Status.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val binding: FragmentMoviesBinding by viewBinding()
    private val viewModel: MovieViewModel by activityViewModels {
        ViewModelFactoryMovie(Injection.movieInjectRepository(requireContext()))
    }
    private val moviesRecyclerviewAdapter by lazy { MoviesRecyclerviewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = moviesRecyclerviewAdapter

        viewModel.getMovies().observe(viewLifecycleOwner) {
            when (it.status) {
                LOADING -> binding.pgMovie.setVisibility(true)
                SUCCESS -> {
                    binding.pgMovie.setVisibility(false)
                    moviesRecyclerviewAdapter.submitList(it.data)
                }
                ERROR -> {
                    binding.pgMovie.setVisibility(true)
                    Toast.makeText(
                        context,
                        getString(R.string.something_when_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}