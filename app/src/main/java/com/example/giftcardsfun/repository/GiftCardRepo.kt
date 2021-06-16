package com.example.giftcardsfun.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.giftcardsfun.db.GiftCardDatabase
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GiftCardRepo {

    companion object {
        var giftCardDatabase: GiftCardDatabase? = null

        var giftCardModel: LiveData<GiftCardEntity>? = null

        fun initializeDB(context: Context): GiftCardDatabase {
            return GiftCardDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, giftCardName: String, firstStoreName: String) {
            giftCardDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val giftCard = GiftCardEntity(arrayListOf("zara, h&m, fox"))
                giftCardDatabase!!.giftCardDao().insertAll(arrayListOf(giftCard))
            }
        }

        fun getGiftCardDetails(context: Context, giftCardName: String): LiveData<GiftCardEntity>? {
            giftCardDatabase = initializeDB(context)

            giftCardModel = giftCardDatabase!!.giftCardDao().getGiftCard("zara")

            return giftCardModel
        }
    }

    @Throws(Exception::class)
    fun refresh() {
        val giftCardModel = network.getGiftCard()
        updateDatabase()
    }
}