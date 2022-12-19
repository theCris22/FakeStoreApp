package com.crisnavarro.fakestore.data

import android.util.Log
import com.crisnavarro.fakestore.data.network.FakeStoreApi
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(val api: FakeStoreApi) {

    suspend fun login(loginRequest: LoginRequest) {
        val call = api.login(loginRequest)

        if (call.isSuccessful)
            Log.e("SUCCESS ->", call.body().toString())
        else
            Log.e("ERROR ->", call.errorBody()!!.string())
    }

}