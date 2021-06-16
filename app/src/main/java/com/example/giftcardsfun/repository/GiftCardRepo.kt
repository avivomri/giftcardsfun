package com.example.giftcardsfun.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giftcardsfun.db.GiftCardDatabase
import com.example.giftcardsfun.db.dao.GiftCardDao
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.*

object GiftCardRepo {
    var giftCardDatabase: GiftCardDatabase? = null
    private lateinit var db: GiftCardDatabase
    private lateinit var giftCardDao: GiftCardDao
    private lateinit var allGiftCards: LiveData<>

    private lateinit var giftCardModel: MutableLiveData<GiftCardEntity>
    fun getGiftCardModel(): LiveData<GiftCardEntity> = giftCardModel

    fun initializeDB(context: Context) {
        db = GiftCardDatabase.getDataseClient(context)
        giftCardDao = db.noteDao()
        allGiftCards = giftCardDao.getAll()
    }

    fun insertData(context: Context, giftCardName: String, firstStoreName: String) {
        giftCardDatabase = initializeDB(context)

        GlobalScope.launch(Dispatchers.IO) {
            val giftCard = GiftCardEntity(arrayListOf("zara, h&m, fox"))
            giftCardDatabase!!.giftCardDao().insertAll(arrayListOf(giftCard))
        }
    }

    fun getGiftCardDetails(context: Context, giftCardName: String): LiveData<GiftCardEntity>? {
        giftCardDatabase = initializeDB(context)

        giftCardModel = giftCardDatabase!!.giftCardDao().getGiftCard("zara")

        return giftCardModel
    }

    @Throws(Exception::class)
    fun refresh() {
        val giftCardModel = network.getGiftCard()
        updateDatabase()
    }
}