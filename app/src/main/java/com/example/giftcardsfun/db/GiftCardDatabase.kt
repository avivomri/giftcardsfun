package com.example.giftcardsfun.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import com.example.giftcardsfun.db.converter.GiftCardsConverter
import com.example.giftcardsfun.db.dao.GiftCardDao
import com.example.giftcardsfun.db.entity.GiftCardEntity

@Database(entities = arrayOf(GiftCardEntity::class), version = 1, exportSchema = false)
//@TypeConverters(GiftCardsConverter::class)
abstract class GiftCardDatabase : RoomDatabase() {

    abstract fun giftCardDao(): GiftCardDao

    companion object {

        @Volatile
        private var INSTANCE: GiftCardDatabase? = null

        fun getDataseClient(context: Context): GiftCardDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, GiftCardDatabase::class.java, "LOGIN_DATABASE")
//                    .addTypeConverter(GiftCardsConverter::class)
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}