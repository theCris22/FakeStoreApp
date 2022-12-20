package com.crisnavarro.fakestore.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import com.crisnavarro.fakestore.domain.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun signup(createUserRequest: CreateUserRequest) = viewModelScope.launch {
        _loading.postValue(true)

        createUserUseCase.invoke(createUserRequest,
            onSuccess = {
                Log.e("SUCCESS ->", it)
            },
            onError = {
                Log.e("ERROR ->", it)
            })

        _loading.postValue(false)
    }

}