package com.emretnkl.week5Assignment.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.emretnkl.week5Assignment.data.model.DataState
import com.emretnkl.week5Assignment.databinding.FragmentUserBinding
import com.emretnkl.week5Assignment.ui.loadingprogress.LoadingProgressBar
import com.emretnkl.week5Assignment.ui.users.adapter.UsersAdapter
import com.emretnkl.week5Assignment.ui.users.viewmodel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding : FragmentUserBinding
    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userLiveData.observe(viewLifecycleOwner){
            when(it){
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvUsersList.adapter = UsersAdapter().apply {
                            submitList(safeData)
                        }
                    } ?: kotlin.run {
                        Toast.makeText(requireContext(),"No Data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root,it.message,Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }


    }

}