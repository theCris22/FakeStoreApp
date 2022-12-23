package com.crisnavarro.fakestore.domain

import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.response.ProfileResponse
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: FakeStoreRepository
) {

    suspend operator fun invoke(
        onSuccess: (profile: ProfileResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {
        repository.getProfile(onSuccess, onError)
    }

}