package com.example.giftcardsfun.viewmodel

import androidx.lifecycle.ViewModel
import com.example.giftcardsfun.repository.GiftCardsRepo

class MainViewModel : ViewModel() {

    fun refresh() {
        GiftCardsRepo.refresh()
    }
}
