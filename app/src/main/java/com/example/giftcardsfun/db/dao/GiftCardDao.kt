package com.example.giftcardsfun.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.network.GiftCardServer

@Dao
interface GiftCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(giftCards: List<GiftCardEntity>)

    @Query("SELECT * FROM GiftCard")
    fun getAll(): LiveData<List<GiftCardEntity>>

    @Query("DELETE FROM GiftCard")
    fun deleteAll()
}