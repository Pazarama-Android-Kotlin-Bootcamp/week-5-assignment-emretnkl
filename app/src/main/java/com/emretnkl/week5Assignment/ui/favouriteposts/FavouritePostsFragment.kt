package com.emretnkl.week5Assignment.ui.favouriteposts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.emretnkl.week5Assignment.R
import com.emretnkl.week5Assignment.data.model.DataState
import com.emretnkl.week5Assignment.databinding.FragmentFavouritePostsBinding
import com.emretnkl.week5Assignment.ui.favouriteposts.adapter.FavouritePostsAdapter
import com.emretnkl.week5Assignment.ui.favouriteposts.viewmodel.FavouritePostsViewModel
import com.emretnkl.week5Assignment.ui.loadingprogress.LoadingProgressBar
import com.emretnkl.week5Assignment.ui.posts.adapter.PostsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePostsFragment : Fragment() {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentFavouritePostsBinding
    private val viewModel by viewModels<FavouritePostsViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouritePostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())

        navController = findNavController()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner){
            when(it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let {
                        val listFavouritePosts = it.filter { it.isFavorite }
                        binding.rvFavouritePostsList.adapter = FavouritePostsAdapter().apply {
                            submitList(listFavouritePosts)
                        }

                    } ?: run {
                        Toast.makeText(requireContext(), "No Data",Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                        loadingProgressBar.hide()
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                    }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }


    }

}