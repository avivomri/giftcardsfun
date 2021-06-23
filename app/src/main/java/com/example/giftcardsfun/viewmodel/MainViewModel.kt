package com.example.giftcardsfun.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.GiftCardsFragment

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    private val mediator = MediatorLiveData<GiftCardsFragment.State>()
    private val repoLiveData: LiveData<GiftCardsFragment.State> =
        Transformations.map(GiftCardRepo.getGiftCardModel()) {
            GiftCardsFragment.State.success
        }
    private val stateLiveData = MutableLiveData<GiftCardsFragment.State>()

    init {
        GiftCardRepo.initializeDB(app)
        mediator.addSource(repoLiveData) { stateLiveData.value = it }
    }


    fun getStateLiveData(): LiveData<GiftCardsFragment.State> = stateLiveData

    fun refresh() = try {
        GiftCardRepo.refresh()

    } catch (e: Exception) {
        stateLiveData.postValue(GiftCardsFragment.State.failure)
    }
}
