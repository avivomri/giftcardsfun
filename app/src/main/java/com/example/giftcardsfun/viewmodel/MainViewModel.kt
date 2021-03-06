package com.example.giftcardsfun.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.repository.GiftCardRepo

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    private val mediator = MediatorLiveData<GiftCardFragmentState>()
    private val repoLiveData: LiveData<GiftCardFragmentState>

    init {
        GiftCardRepo.initializeDB(app)

        repoLiveData = Transformations.map(GiftCardRepo.getGiftCardModels()) {
            GiftCardFragmentState.Success(it)
        }

        mediator.addSource(repoLiveData) { mediator.value = it }
    }

    fun getStateLiveData(): LiveData<GiftCardFragmentState> = mediator

    fun getGiftCardModels(): List<GiftCardEntity> = GiftCardRepo.getGiftCardModels().value ?: emptyList()

    fun refresh() = try {
        GiftCardRepo.refresh()
    } catch (e: Exception) {
        mediator.postValue(GiftCardFragmentState.Failure)
    }
}
