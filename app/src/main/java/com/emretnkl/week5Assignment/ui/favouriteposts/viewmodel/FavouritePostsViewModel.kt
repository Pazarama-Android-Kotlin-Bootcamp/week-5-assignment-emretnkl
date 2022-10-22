package com.emretnkl.week5Assignment.ui.favouriteposts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emretnkl.week5Assignment.data.model.DataState
import com.emretnkl.week5Assignment.data.model.Post
import com.emretnkl.week5Assignment.data.model.PostDTO
import com.emretnkl.week5Assignment.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavouritePostsViewModel @Inject constructor(private val postRepository: PostRepository) :ViewModel() {

    private var _postLiveData = MutableLiveData<DataState<List<PostDTO>?>>()
    val postLiveData: LiveData<DataState<List<PostDTO>?>>
        get() = _postLiveData

    init {
        getPosts()
    }

    private fun getPosts() {
        _postLiveData.postValue(DataState.Loading())
        postRepository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        _postLiveData.postValue(DataState.Success(it.map { safePost ->
                            PostDTO(
                                id = safePost.id,
                                title = safePost.title,
                                body = safePost.body,
                                userId = safePost.userId,
                                isFavorite = isExists(safePost.id)
                            )

                        }))
                    } ?: kotlin.run {
                        _postLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _postLiveData.postValue(DataState.Error(response.message()))
                }
            }



            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _postLiveData.postValue(DataState.Error(t.message.toString()))
            }

        }
        )
    }
    private fun isExists(postId: Int?): Boolean {
        postId?.let {
            postRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }
}