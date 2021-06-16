package com.example.giftcardsfun.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.giftcardsfun.model.GiftCard

@Entity(tableName = "GiftCard")
data class GiftCardEntity constructor(@ColumnInfo(name = "store_name") private val storeName: String) :
    GiftCard {

    @ColumnInfo(name = "name")
    private lateinit var name: String

    override fun getName(): String {
        return name
    }

    override fun getStoreName(): String {
        return storeName
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: String? = null
}