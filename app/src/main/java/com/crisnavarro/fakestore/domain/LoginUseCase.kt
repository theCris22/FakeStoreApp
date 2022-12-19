package com.crisnavarro.fakestore.domain

import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.request.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: FakeStoreRepository) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) =
        repository.login(LoginRequest(userName, password), onSuccess, onError)

}