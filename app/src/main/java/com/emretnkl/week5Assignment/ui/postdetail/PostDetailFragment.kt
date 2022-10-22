package com.emretnkl.week5Assignment.ui.postdetail

import android.os.Bundle
import android.util.Log
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
import com.emretnkl.week5Assignment.data.model.PostDTO
import com.emretnkl.week5Assignment.databinding.FragmentPostDetailBinding
import com.emretnkl.week5Assignment.ui.loadingprogress.LoadingProgressBar
import com.emretnkl.week5Assignment.ui.postdetail.viewmodel.PostDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding : FragmentPostDetailBinding
    private val viewModel by viewModels<PostDetailViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postModelJson = arguments?.getString("postModel")
        val postModel = postModelJson?.let { PostDTO.fromJson(it) }
        val postId = arguments?.getString("postId")!!.toInt()

        loadingProgressBar = LoadingProgressBar(requireContext())
        navController = findNavController()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner){
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let {

                        binding.tvDetailBody.text = it[postId - 1].body
                        binding.tvDetailTitle.text = it[postId - 1].title
                        binding.tvDetailUserId.text = it[postId - 1].userId.toString()
                        if (it[postId - 1].isFavorite){
                            binding.ivFavourite.setImageResource(R.drawable.ic_red_favorite)

                        }

                    } ?: run {
                        Toast.makeText(requireContext(),"No Data",Toast.LENGTH_SHORT).show()
                }
            }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()

                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
                null -> TODO()
            }
        }

        binding.ivFavourite.setOnClickListener {
            if (postModel != null) {
                viewModel.onFavoritePost(postModel)
            }
        }


    }

}