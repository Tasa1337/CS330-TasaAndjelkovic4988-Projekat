package com.example.cs330_tasaandjelkovic4988_pz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =  [Kontakt::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun kontaktDao(): KontaktDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase ::class.java,
                    "Kontakti Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}