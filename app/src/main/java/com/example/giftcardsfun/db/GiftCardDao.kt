package com.example.giftcardsfun.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.giftcardsfun.model.GiftCardModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(giftCardModel: GiftCardModel)

    @Query("SELECT * FROM GiftCard WHERE store_name =:storeName")
    fun getGiftCard(storeName: String) : LiveData<GiftCardModel>

}