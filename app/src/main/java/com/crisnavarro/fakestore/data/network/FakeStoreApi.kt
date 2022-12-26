package com.crisnavarro.fakestore.data.network

import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import com.crisnavarro.fakestore.data.network.response.GetProductsResponse
import com.crisnavarro.fakestore.data.network.response.LoginResponse
import com.crisnavarro.fakestore.data.network.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeStoreApi {

    @POST("/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest): Response<String>

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/products")
    suspend fun getAllProducts(): Response<GetProductsResponse>

    @GET("/users/")
    suspend fun getProfile(
        @Query("id") id: String = "10"
    ): Response<ProfileResponse>

    @GET("/products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): Response<GetProductsResponse>

}