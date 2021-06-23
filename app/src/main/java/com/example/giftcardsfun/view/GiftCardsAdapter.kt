package com.example.giftcardsfun.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.giftcardsfun.R
import com.example.giftcardsfun.databinding.GiftCardRowLayoutBinding
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.Dispatchers

class GiftCardsAdapter(giftCards: List<GiftCardEntity>): RecyclerView.Adapter<GiftCardsAdapter.GiftCardRowHolder>() {
    private val giftCards: MutableList<GiftCardEntity> = ArrayList(giftCards)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftCardRowHolder {
        val binding: GiftCardRowLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.gift_card_row_layout, parent, false)
        return GiftCardRowHolder(binding)
    }

    override fun onBindViewHolder(holder: GiftCardRowHolder, position: Int) {
        val giftCard = giftCards[position]
        holder.binding(giftCard)
    }

    override fun getItemCount(): Int {
        return giftCards.size
    }

    fun setGiftCards(giftCards: List<GiftCardEntity>) {
        this.giftCards.clear()
        this.giftCards.addAll(giftCards)
        notifyDataSetChanged()
    }

    fun clear() {
        setGiftCards(mutableListOf())
    }

    //View Holder
    inner class GiftCardRowHolder(private val binding: GiftCardRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun binding(giftCard: GiftCardEntity) {
            binding.model = giftCard
        }
    }
}