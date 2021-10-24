package com.abeltarazona.bbvacryptowallet.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.Crypto
import com.abeltarazona.bbvacryptowallet.R
import com.abeltarazona.bbvacryptowallet.databinding.ItemCriptoComprarBinding
import com.abeltarazona.bbvacryptowallet.inflate
import com.bumptech.glide.Glide

/**
 * Created by AbelTarazona on 23/10/2021
 */
class CryptoAdapter(val onClick: (Crypto) -> Unit) : ListAdapter<Crypto, CryptoAdapter.Holder>(CryptoDiffCallback()) {

    var selectedItemPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_cripto_comprar)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        if (position == selectedItemPos) {
            holder.selected()
        } else {
            holder.unselected()
        }

        holder.bind(item)

        holder.itemView.setOnClickListener {
            selectedItemPos = position
            notifyDataSetChanged()
            onClick(item)
        }
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCriptoComprarBinding.bind(view)

        fun bind(data: Crypto) {
            with(binding) {
                Glide.with(itemView).load(data.icon).into(imageView14)
            }
        }

        fun selected() {
            binding.imageView15.visibility = View.VISIBLE
        }

        fun unselected() {
            binding.imageView15.visibility = View.INVISIBLE
        }
    }

}

class CryptoDiffCallback : DiffUtil.ItemCallback<Crypto>() {
    override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}