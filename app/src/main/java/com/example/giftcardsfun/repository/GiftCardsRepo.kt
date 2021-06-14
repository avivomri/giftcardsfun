package com.example.giftcardsfun.repository

import java.lang.Exception

object GiftCardsRepo {

    @Throws(Exception::class)
    fun refresh() {
        val giftCardModel = network.getGiftCard()
        updateDatabase()
    }
}