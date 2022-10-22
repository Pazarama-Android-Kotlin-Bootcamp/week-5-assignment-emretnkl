package com.emretnkl.week5Assignment.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emretnkl.week5Assignment.data.model.DataState
import com.emretnkl.week5Assignment.data.model.Users
import com.emretnkl.week5Assignment.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var _userLiveData = MutableLiveData<DataState<List<Users>?>>()
    val userLiveData : LiveData<DataState<List<Users>?>>
        get() = _userLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        _userLiveData.postValue(DataState.Loading())
        userRepository.getUsers().enqueue( object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        _userLiveData.postValue(DataState.Success(it))
                    } ?: kotlin.run {
                        _userLiveData.postValue(DataState.Error("Data Empty"))
                    }
                }else{
                    _userLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _userLiveData.postValue(DataState.Error(t.message.toString()))
            }

        })

    }
}
