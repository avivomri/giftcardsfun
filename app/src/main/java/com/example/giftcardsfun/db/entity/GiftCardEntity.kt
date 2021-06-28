package com.example.giftcardsfun.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.giftcardsfun.model.GiftCard

@Entity(tableName = "GiftCard")
data class GiftCardEntity constructor(
    @ColumnInfo(name = "store_name")
    var storeName: String,
    @ColumnInfo(name = "name")
    var cardName: String = "MAX"
    ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int = 0

    override fun toString(): String {
        return "store = $storeName; card = $cardName; id = $Id"
    }
}