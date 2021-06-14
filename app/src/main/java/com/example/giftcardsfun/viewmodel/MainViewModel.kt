package com.example.giftcardsfun.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giftcardsfun.repository.GiftCardsRepo
import com.example.giftcardsfun.view.MainFragment

class MainViewModel : ViewModel() {
    private lateinit var stateLiveData: MutableLiveData<MainFragment.State>

    fun refresh() = try {

        GiftCardsRepo.refresh()

    } catch (e: Exception) {
        stateLiveData.postValue(MainFragment.State.failure)
    }
}
