package com.example.giftcardsfun.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.MainFragment

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    init {
        GiftCardRepo.initializeDB(app)
    }

    private /*lateinit*/ var stateLiveData: MutableLiveData<MainFragment.State> = MutableLiveData()
    fun getStateLiveData(): LiveData<MainFragment.State> = stateLiveData

    fun refresh() = try {
        GiftCardRepo.refresh()

    } catch (e: Exception) {
        stateLiveData.postValue(MainFragment.State.failure)
    }
}
