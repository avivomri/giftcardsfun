package com.example.giftcardsfun.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.GiftCardsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    private val mediator = MediatorLiveData<GiftCardsFragment.State>()
    private val repoLiveData: LiveData<GiftCardsFragment.State>
    private val stateLiveData = MutableLiveData<GiftCardsFragment.State>()

    init {
        GiftCardRepo.initializeDB(app)
        repoLiveData = Transformations.map(GiftCardRepo.getGiftCardModels()) {
                GiftCardsFragment.State.Success(it)
            }
        mediator.addSource(repoLiveData) { stateLiveData.value = it }
    }


    fun getStateLiveData(): LiveData<GiftCardsFragment.State> = stateLiveData

    fun getGiftCardModels(): List<GiftCardEntity> = GiftCardRepo.getGiftCardModels().value!!

    fun refresh() = try {
        GiftCardRepo.refresh()
    } catch (e: Exception) {
        stateLiveData.postValue(GiftCardsFragment.State.Failure)
    }
}
