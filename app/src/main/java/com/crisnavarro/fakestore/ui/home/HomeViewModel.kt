package com.crisnavarro.fakestore.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.fakestore.data.network.models.Product
import com.crisnavarro.fakestore.domain.GetAllProductsUseCase
import com.crisnavarro.fakestore.domain.ProductByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val productByCategoryUseCase: ProductByCategoryUseCase
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    private val _products = MutableLiveData<ArrayList<Product>>()
    var products: LiveData<ArrayList<Product>> = _products

    fun getAllProducts() = viewModelScope.launch {
        _loading.postValue(true)

        getAllProductsUseCase.invoke(
            onSuccess = {
                _products.postValue(it)
            },
            onError = {
                _products.postValue(arrayListOf())
                Log.e("ERROR ->", it)
            }
        )
        _loading.postValue(false)
    }

    fun productsByCategory(category: String) = viewModelScope.launch {
        _loading.postValue(true)

        productByCategoryUseCase.invoke(category,
            onSuccess = {
                _products.postValue(it)
            },
            onError = {
                _products.postValue(arrayListOf())
                Log.e("ERROR ->", it)
            }
        )
        _loading.postValue(false)
    }

}