package com.example.giftcardsfun.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giftcardsfun.db.GiftCardDatabase
import com.example.giftcardsfun.db.dao.GiftCardDao
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.network.GiftCardServer
import com.example.giftcardsfun.network.RestGiftCardService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object GiftCardRepo {
    private lateinit var db: GiftCardDatabase
    private lateinit var giftCardDao: GiftCardDao
    private lateinit var allGiftCards: LiveData<List<GiftCardEntity>>
    private lateinit var restService: RestGiftCardService

    fun getGiftCardModels(): LiveData<List<GiftCardEntity>> = allGiftCards

    fun initializeDB(context: Context) {
        db = GiftCardDatabase.getDataseClient(context)
        giftCardDao = db.giftCardDao()
        allGiftCards = giftCardDao.getAll()

        if (allGiftCards.value?.size ?: 0 == 0) {
            fetchFromServer()
        }
    }

    private fun fetchFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            val giftCardServer: GiftCardServer = restService.getStores()
            mergeToDb(giftCardServer)
        }
    }

    private fun mergeToDb(giftCardServer: GiftCardServer) {
        //todo finish merge method
        giftCardDao.deleteAll()
        giftCardDao.insertAll(giftCardServer.stores)
    }

    fun getAllGiftCard(): LiveData<List<GiftCardEntity>> {
        return allGiftCards
    }

    fun insertData(context: Context, giftCardName: String, firstStoreName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            db.giftCardDao().insertAll(
                giftCards = arrayListOf("zara", "fox").map { store -> GiftCardEntity(store) })
        }
    }

//    fun getGiftCardDetails(context: Context, giftCardName: String): LiveData<GiftCardEntity>? {
//        giftCardModel = db.giftCardDao().getGiftCard("zara")
//
//        return giftCardModel
//    }

    @Throws(Exception::class)
    fun refresh() {
        fetchFromServer()
    }
}