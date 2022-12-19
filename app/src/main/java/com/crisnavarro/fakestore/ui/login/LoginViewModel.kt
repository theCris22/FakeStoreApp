package com.crisnavarro.fakestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import com.crisnavarro.fakestore.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    fun login(userName: String, password: String) = viewModelScope.launch {
        _loading.postValue(true)

        loginUseCase.invoke(userName, password,
            onSuccess = {

            }, onError = {

            }
        )
        _loading.postValue(false)
    }

}