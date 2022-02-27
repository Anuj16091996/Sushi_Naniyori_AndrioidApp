package com.example.final_pratical_exam.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.final_pratical_exam.db.dao.MenuDAO
import com.reza.roomapplication.db.entities.MenuDatabase
import java.util.concurrent.Executors

//TODO: Add Entities and change version if you modified the database
@Database(entities = [MenuDatabase::class], version = 13)
abstract class AppDatabase : RoomDatabase() {

    //TODO: Add DAOs here
    //DAO (Database Access Objects)
    abstract fun MenuDAO(): MenuDAO

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        private var INSTANCE: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() //Modified/Changed this will destroy the DB and recreate it.
                    .build()

                INSTANCE = instance

                instance //returning
            }
        }
    }

}