package com.crisnavarro.fakestore.data.network

import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FakeStoreApi {

    @POST("/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest): Response<String>

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<String>

}