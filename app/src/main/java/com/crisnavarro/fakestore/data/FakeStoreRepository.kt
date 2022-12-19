package com.crisnavarro.fakestore.data

import android.util.Log
import com.crisnavarro.fakestore.data.network.FakeStoreApi
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(private val api: FakeStoreApi) {

    suspend fun login(loginRequest: LoginRequest, onSuccess: () -> Unit, onError: () -> Unit) {
        val call = api.login(loginRequest)

        if (call.isSuccessful)
            onSuccess()
        else
            onError()
    }

}