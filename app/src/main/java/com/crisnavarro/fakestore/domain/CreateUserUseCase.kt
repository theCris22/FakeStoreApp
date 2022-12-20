package com.crisnavarro.fakestore.domain

import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: FakeStoreRepository) {

    suspend operator fun invoke(
        createUserRequest: CreateUserRequest,
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit
    ) = repository.signup(createUserRequest, onSuccess, onError)


}