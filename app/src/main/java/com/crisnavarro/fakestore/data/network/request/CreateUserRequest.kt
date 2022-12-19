package com.crisnavarro.fakestore.data.network.request

import com.crisnavarro.fakestore.data.network.models.Address
import com.crisnavarro.fakestore.data.network.models.UserInformation


data class CreateUserRequest(
    val address: Address,
    val email: String,
    val name: UserInformation,
    val password: String,
    val username: String
)