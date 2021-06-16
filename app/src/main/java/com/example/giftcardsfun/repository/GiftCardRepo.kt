package com.example.giftcardsfun.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giftcardsfun.db.GiftCardDatabase
import com.example.giftcardsfun.db.dao.GiftCardDao
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object GiftCardRepo {
    var giftCardDatabase: GiftCardDatabase? = null
    private lateinit var db: GiftCardDatabase
    private lateinit var giftCardDao: GiftCardDao
    private lateinit var allGiftCards: LiveData<GiftCardEntity>

    private lateinit var giftCardModel: MutableLiveData<GiftCardEntity>
    fun getGiftCardModel(): LiveData<GiftCardEntity> = giftCardModel

    fun initializeDB(context: Context) {
        db = GiftCardDatabase.getDataseClient(context)
        giftCardDao = db.giftCardDao()
        allGiftCards = giftCardDao.getAll()
    }

    fun insertData(context: Context, giftCardName: String, firstStoreName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            giftCardDatabase!!.giftCardDao().insertAll(
                giftCards = arrayListOf("zara", "fox").map { store -> GiftCardEntity(store) })
        }
    }

    fun getGiftCardDetails(context: Context, giftCardName: String): LiveData<GiftCardEntity>? {
        giftCardModel = giftCardDatabase!!.giftCardDao().getGiftCard("zara")

        return giftCardModel
    }

    @Throws(Exception::class)
    fun refresh() {
        val giftCardModel = network.getGiftCard()
        updateDatabase()
    }
}