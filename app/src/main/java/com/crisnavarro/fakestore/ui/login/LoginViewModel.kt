package com.crisnavarro.fakestore.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    ViewModel() {

    /*fun login(userName: String, password: String) = viewModelScope.launch {
        repository.login(LoginRequest(userName, password))
    }*/

}