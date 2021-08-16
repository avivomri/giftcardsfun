package com.example.giftcardsfun.network

import android.util.Log
import com.google.firebase.database.*
import kotlinx.coroutines.delay

class RestGiftCardService {
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    suspend fun getStores(): GiftCardServer {
        printAllFirebaseDB()
        //Mock
        delay(1500)
        val stores: List<String> = listOf("Zara", "Fox")
        return GiftCardServer(stores, "MAX")
    }

    fun printAllFirebaseDB() {
        val dbRef = firebaseDatabase.reference
        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("CARD", "DB state:")
                    for (d in dataSnapshot.children) {
                        val key = d.key
                        val obj = d.value
                        Log.d("CARD", "($key : $obj)")
                    }
                }
            } //onDataChange

            override fun onCancelled(error: DatabaseError) {
                Log.d("CARD", "onCancelled")
            } //onCancelled
        })
    }
}