package com.crisnavarro.fakestore.data.network.response

import com.crisnavarro.fakestore.data.network.models.Address
import com.crisnavarro.fakestore.data.network.models.UserInformation

class ProfileResponse : ArrayList<ProfileResponse.ProfileResponseItem>(){
    data class ProfileResponseItem(
        val __v: Int,
        val address: Address,
        val email: String,
        val id: Int,
        val name: UserInformation,
        val password: String,
        val username: String
    )

}