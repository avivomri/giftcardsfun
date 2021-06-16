package com.example.giftcardsfun.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.giftcardsfun.model.GiftCard

@Entity(tableName = "GiftCard")
data class GiftCardEntity constructor(@ColumnInfo(name = "store_name") private val storeNames: List<String>) :
    GiftCard {

    @ColumnInfo(name = "name")
    private var name: String? = null

    override fun getName(): String? {
        return name
    }

    override fun getStoreNames(): List<String>? {
        return storeNames
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: String? = null
}