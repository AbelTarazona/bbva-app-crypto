package com.abeltarazona.bbvacryptowallet

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.databinding.ItemMarketPriceBinding
import com.bumptech.glide.Glide

/**
 * Created by AbelTarazona on 23/10/2021
 */
class MarketPriceAdapter :
    ListAdapter<Crypto, MarketPriceAdapter.Holder>(MarketPriceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_market_price)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMarketPriceBinding.bind(view)

        fun bind(data: Crypto) {
            with(binding) {
                Glide.with(itemView).load(data.icon).into(imageView4)
                textView15.text = data.name
                textView16.text = "S/ ${data.priceStr}"
                if (data.variation <= 0) {
                    textView17.text = "- ${data.variation.toString().drop(1)} %"
                    textView17.setTextColor(itemView.resources.getColor(R.color.decrease))
                } else {
                    textView17.text = "+ ${data.variation} %"
                    textView17.setTextColor(itemView.resources.getColor(R.color.increase))
                }
            }
        }
    }

}

class MarketPriceDiffCallback : DiffUtil.ItemCallback<Crypto>() {
    override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}