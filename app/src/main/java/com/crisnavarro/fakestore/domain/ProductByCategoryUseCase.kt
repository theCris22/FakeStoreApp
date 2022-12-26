package com.crisnavarro.fakestore.domain

import com.crisnavarro.fakestore.data.FakeStoreRepository
import com.crisnavarro.fakestore.data.network.models.Product
import javax.inject.Inject

class ProductByCategoryUseCase @Inject constructor(
    private val repository: FakeStoreRepository
) {

    suspend operator fun invoke(
        category: String,
        onSuccess: (list: ArrayList<Product>) -> Unit,
        onError: (message: String) -> Unit
    ) = repository.getProductsByCategory(category, onSuccess, onError)

}