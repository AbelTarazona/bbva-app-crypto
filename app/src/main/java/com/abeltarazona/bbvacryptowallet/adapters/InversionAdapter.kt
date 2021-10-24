package com.abeltarazona.bbvacryptowallet.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.R
import com.abeltarazona.bbvacryptowallet.databinding.ItemInversionBinding
import com.abeltarazona.bbvacryptowallet.inflate
import com.abeltarazona.bbvacryptowallet.models.Inversion
import com.bumptech.glide.Glide

/**
 * Created by AbelTarazona on 24/10/2021
 */
class InversionAdapter :
    ListAdapter<Inversion, InversionAdapter.Holder>(InversionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_inversion)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemInversionBinding.bind(view)

        fun bind(data: Inversion) {
            with(binding) {
                Glide.with(itemView).load(data.img).into(imageView5)
                title.text = data.nameLarge
                amount.text = data.getAmountCriptoFormatted()
                textView18.text = data.getAmountSolesFormatted()
            }
        }
    }

}

class InversionDiffCallback : DiffUtil.ItemCallback<Inversion>() {
    override fun areItemsTheSame(oldItem: Inversion, newItem: Inversion): Boolean {
        return oldItem.nameLarge == newItem.nameLarge
    }

    override fun areContentsTheSame(oldItem: Inversion, newItem: Inversion): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}