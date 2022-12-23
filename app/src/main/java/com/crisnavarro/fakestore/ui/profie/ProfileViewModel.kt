package com.crisnavarro.fakestore.ui.profie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.data.network.response.ProfileResponse
import com.crisnavarro.fakestore.domain.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase
) : ViewModel() {

    private var _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private var _profile = MutableLiveData<ProfileResponse>()
    val profile: LiveData<ProfileResponse> get() = _profile

    fun getProfile() = viewModelScope.launch {
        _loading.postValue(true)

        profileUseCase.invoke(
            onSuccess = {
                _profile.postValue(it)
            },
            onError = {
                Log.e("ERROR", it)
            })

        _loading.postValue(false)
    }

}