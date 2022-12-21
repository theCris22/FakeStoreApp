package com.crisnavarro.fakestore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.core.hide
import com.crisnavarro.fakestore.core.show
import com.crisnavarro.fakestore.databinding.FragmentHomeBinding
import com.crisnavarro.fakestore.ui.adapters.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var itemsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() = with(binding!!) {
        itemsAdapter = ProductsAdapter()
        viewModel.getAllProducts()
        rvShopItems.apply {
            adapter = itemsAdapter
            setHasFixedSize(true)
        }
    }

    private fun initListeners() = with(binding!!) {
        swipeLayout.setOnRefreshListener { viewModel.getAllProducts() }
    }

    private fun initObservers() = with(binding!!) {
        viewModel.loading.observe(viewLifecycleOwner) {
            swipeLayout.isRefreshing = it
        }
        viewModel.products.observe(viewLifecycleOwner) {
            if (it.any())
                itemsAdapter.submitList(it)
                    .also { view?.show(chipGroup, rvShopItems); view?.hide(ivEmpty) }
            else
                view?.hide(chipGroup, rvShopItems).also { view?.show(ivEmpty) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}