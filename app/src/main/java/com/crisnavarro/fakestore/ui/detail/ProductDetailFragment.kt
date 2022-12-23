package com.crisnavarro.fakestore.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var binding: FragmentProductDetailBinding? = null
    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() = with(binding!!) {
        val product = args.product

        Glide.with(requireContext()).load(product.image).into(ivItem)
        tvTitle.text = product.title
        tvDescription.text = product.description
        tvPrice.text = product.price.toString().format("%.2d")
        ratingBar.rating = product.rating.rate.toFloat()
    }

    private fun initListeners() = with(binding!!) {
        ivClose.setOnClickListener { findNavController().popBackStack() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}