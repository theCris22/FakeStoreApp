package com.crisnavarro.fakestore.domain

import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.models.Product
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val repository: FakeStoreRepository) {

    suspend operator fun invoke(
        onSuccess: (products: ArrayList<Product>) -> Unit,
        onError: (error: String) -> Unit
    ) = repository.getAllProducts(onSuccess, onError)

}