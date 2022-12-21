package com.crisnavarro.fakestore.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.data.network.models.Product
import com.crisnavarro.fakestore.databinding.ItemRowBinding

class ProductsAdapter :
    ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(ProductsDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.setData(currentList[position])
    }

    class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemRowBinding.bind(view)

        fun setData(product: Product) = with(binding) {
            Glide.with(itemView).load(product.image).placeholder(R.drawable.icon_empty).into(ivItem)
            itemTitle.text = product.title
            itemPrice.text = "${product.price}".format("%.2f")
        }

    }

    object ProductsDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

    }
}