package com.emretnkl.week5Assignment.ui.posts

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
import com.google.android.material.snackbar.Snackbar
import com.emretnkl.week5Assignment.data.model.DataState
import com.emretnkl.week5Assignment.data.model.Post
import com.emretnkl.week5Assignment.data.model.PostDTO
import com.emretnkl.week5Assignment.databinding.FragmentPostsBinding
import com.emretnkl.week5Assignment.ui.loadingprogress.LoadingProgressBar
import com.emretnkl.week5Assignment.ui.posts.adapter.OnPostClickListener
import com.emretnkl.week5Assignment.ui.posts.adapter.PostsAdapter
import com.emretnkl.week5Assignment.ui.posts.viewmodel.PostViewEvent
import com.emretnkl.week5Assignment.ui.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), OnPostClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentPostsBinding
    private val viewModel by viewModels<PostsViewModel>()
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())

        navController = findNavController()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.postLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvPostsList.adapter = PostsAdapter(this@PostsFragment).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is PostViewEvent.ShowMessage -> {}
                is PostViewEvent.NavigateToDetail -> {}
            }
        }

    }

    override fun onPostClick(post: PostDTO) {
        viewModel.onFavoritePost(post)
    }

    override fun onPostBodyClick(post: PostDTO) {
        navController.navigate(R.id.action_postsFragment_to_postDetailFragment,Bundle().apply {

            putString("postModel",post.toJson())
            putString("postId",post.id.toString())
        })
    }
}

