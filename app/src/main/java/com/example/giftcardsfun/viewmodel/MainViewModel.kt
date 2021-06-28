package com.example.giftcardsfun.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.repository.GiftCardRepo
import com.example.giftcardsfun.view.GiftCardsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel constructor(app: Application) : AndroidViewModel(app) {
    private val mediator = MediatorLiveData<GiftCardFragmentState>()
    private val repoLiveData: LiveData<GiftCardFragmentState>
    private val stateLiveData = MutableLiveData<GiftCardFragmentState>()

    //for debug
    private lateinit var allGiftCards: LiveData<List<GiftCardEntity>>

    init {
        GiftCardRepo.initializeDB(app)

        //works
//        allGiftCards = GiftCardRepo.getGiftCardModels()
//        allGiftCards.observeForever { giftCard ->
//            Log.d("CARD", giftCard.toString())
//        }

        repoLiveData = Transformations.map(GiftCardRepo.getGiftCardModels()) {
            GiftCardFragmentState.Success(it)
            }

        //works
//        repoLiveData.observeForever { state ->
//            Log.d("CARD", state.toString())
//        }

        mediator.addSource(repoLiveData) { stateLiveData.value = it }
    }


    fun getStateLiveData(): LiveData<GiftCardFragmentState> = stateLiveData

    fun getMediator(): LiveData<GiftCardFragmentState> = mediator //doesn't work at the moment

    fun getGiftCardModels(): List<GiftCardEntity> = GiftCardRepo.getGiftCardModels().value ?: emptyList()

    fun refresh() = try {
        GiftCardRepo.refresh()
    } catch (e: Exception) {
        stateLiveData.postValue(GiftCardFragmentState.Failure)
    }
}
