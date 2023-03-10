package com.crisnavarro.fakestore.data

import com.crisnavarro.fakestore.data.network.FakeStoreApi
import com.crisnavarro.fakestore.data.network.models.Product
import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import com.crisnavarro.fakestore.data.network.response.ProfileResponse
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(private val api: FakeStoreApi) {

    suspend fun login(
        loginRequest: LoginRequest,
        onSuccess: (token: String) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val call = api.login(loginRequest)
            if (call.isSuccessful)
                onSuccess(call.body()?.token.toString())
            else
                onError(call.errorBody()?.string().toString())
        } catch (ex: Exception) {
            onError(ex.cause.toString())
        }
    }

    suspend fun signup(
        createUserRequest: CreateUserRequest,
        onSuccess: (token: String) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val call = api.createUser(createUserRequest)
            if (call.isSuccessful)
                onSuccess(call.body().toString())
            else
                onError(call.errorBody()?.string().toString())
        } catch (ex: Exception) {
            onError(ex.cause.toString())
        }
    }

    suspend fun getAllProducts(
        onSuccess: (products: ArrayList<Product>) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val call = api.getAllProducts()
            if (call.isSuccessful)
                onSuccess(call.body() ?: arrayListOf())
            else
                onError(call.errorBody()?.string().toString())
        } catch (ex: Exception) {
            onError(ex.cause.toString())
        }
    }

    suspend fun getProfile(
        onSuccess: (profile: ProfileResponse) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val call = api.getProfile()
            if (call.isSuccessful)
                onSuccess(call.body()!!)
            else
                onError(call.errorBody()?.string().toString())
        } catch (ex: Exception) {
            onError(ex.cause.toString())
        }
    }

    suspend fun getProductsByCategory(
        category: String,
        onSuccess: (products: ArrayList<Product>) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            val call = api.getProductsByCategory(category)
            if (call.isSuccessful)
                onSuccess(call.body() ?: arrayListOf())
            else
                onError(call.errorBody()?.string().toString())
        } catch (ex: Exception) {
            onError(ex.cause.toString())
        }
    }

}