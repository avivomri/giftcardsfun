package com.example.giftcardsfun.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GiftCard")
data class GiftCardModel(

    @ColumnInfo(name = "store_name")
    var StoreName: List<String>

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: String? = null
}