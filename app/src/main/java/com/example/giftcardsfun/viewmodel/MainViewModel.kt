package com.example.giftcardsfun.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.MainFragment

class MainViewModel constructor(val context: Context?) : ViewModel() {
    init {
        //
    }

    companion object {

    }

    private /*lateinit*/ var stateLiveData: MutableLiveData<MainFragment.State> = MutableLiveData()
    fun getStateLiveData(): LiveData<MainFragment.State> = stateLiveData

    fun refresh() = try {
        GiftCardRepo.refresh()

    } catch (e: Exception) {
        stateLiveData.postValue(MainFragment.State.failure)
    }
}
