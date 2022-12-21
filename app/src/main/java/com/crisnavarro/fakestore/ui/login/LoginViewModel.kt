package com.crisnavarro.fakestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    private val _successLogin = MutableLiveData(false)
    var successLogin: LiveData<Boolean> = _successLogin

    private val _failLogin = MutableLiveData("")
    var failLogin: LiveData<String> = _failLogin

    fun login(userName: String, password: String) = viewModelScope.launch {
        _loading.postValue(true)

        loginUseCase.invoke(userName, password,
            onSuccess = { _successLogin.postValue(true) },
            onError = { _failLogin.postValue(it) }
        )

        _loading.postValue(false)
    }

}