package com.example.giftcardsfun.viewmodel

import com.example.giftcardsfun.db.entity.GiftCardEntity

sealed class GiftCardFragmentState {
    data class Success(val list: List<GiftCardEntity>) : GiftCardFragmentState()
    object Failure : GiftCardFragmentState()
}