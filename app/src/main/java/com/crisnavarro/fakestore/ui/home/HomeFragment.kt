package com.crisnavarro.fakestore.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.core.hide
import com.crisnavarro.fakestore.core.show
import com.crisnavarro.fakestore.data.network.models.Product
import com.crisnavarro.fakestore.databinding.FragmentHomeBinding
import com.crisnavarro.fakestore.ui.adapters.ProductsAdapter
import com.google.android.material.chip.Chip
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
        itemsAdapter = ProductsAdapter() { onClickProduct(it) }
        viewModel.getAllProducts()
        rvShopItems.apply {
            adapter = itemsAdapter
            setHasFixedSize(true)
        }
    }

    private fun onClickProduct(it: Product) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                it
            )
        )
    }

    private fun initListeners() = with(binding!!) {
        swipeLayout.setOnRefreshListener { viewModel.getAllProducts() }
        ivProfile.setOnClickListener { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment()) }
        ivCart.setOnClickListener { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCartFragment()) }
        chipElectronic.setOnClickListener {
            viewModel.productsByCategory(
                chipElectronic.text.toString().lowercase()
            )
        }
        chipJewelery.setOnClickListener {
            viewModel.productsByCategory(
                chipJewelery.text.toString().lowercase()
            )
        }
        chipMan.setOnClickListener {
            viewModel.productsByCategory(
                chipMan.text.toString().lowercase()
            )
        }
        chipWoman.setOnClickListener {
            viewModel.productsByCategory(
                chipWoman.text.toString().lowercase()
            )
        }
    }

    private fun initObservers() = with(binding!!) {
        viewModel.loading.observe(viewLifecycleOwner) {
            swipeLayout.isRefreshing = it
        }
        viewModel.products.observe(viewLifecycleOwner) {
            if (it.any())
                itemsAdapter.submitList(it)
                    .also { view?.show(hsv, rvShopItems); view?.hide(ivEmpty) }
            else
                view?.hide(hsv, rvShopItems).also { view?.show(ivEmpty) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}