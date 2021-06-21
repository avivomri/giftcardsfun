package com.example.giftcardsfun.network

import kotlinx.coroutines.delay

class RestGiftCardService {
    suspend fun getStores(): GiftCardServer {
        //Mock
        delay(1500)
        val stores: List<String> = listOf("zara", "fox")
        return GiftCardServer(stores, "MAX")
    }
}