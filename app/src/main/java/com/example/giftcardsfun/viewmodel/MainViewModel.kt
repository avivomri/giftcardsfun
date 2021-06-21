package com.example.giftcardsfun.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.MainFragment

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    private val mediator = MediatorLiveData<MainFragment.State>()
    private val repoLiveData: LiveData<MainFragment.State> =
        Transformations.map(GiftCardRepo.getGiftCardModel()) {
            MainFragment.State.success
        }
    private val stateLiveData = MutableLiveData<MainFragment.State>()

    init {
        GiftCardRepo.initializeDB(app)
        mediator.addSource(repoLiveData) { stateLiveData.value = it }
    }


    fun getStateLiveData(): LiveData<MainFragment.State> = stateLiveData

    fun refresh() = try {
        GiftCardRepo.refresh()

    } catch (e: Exception) {
        stateLiveData.postValue(MainFragment.State.failure)
    }
}
