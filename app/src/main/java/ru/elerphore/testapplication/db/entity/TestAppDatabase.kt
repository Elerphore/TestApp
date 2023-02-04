package ru.elerphore.testapplication.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ReviewDBEntity::class], version = 1)
abstract class TestAppDatabase : RoomDatabase() {

    abstract fun reviewDao() : ReviewDao

}

object DB {
    var database: TestAppDatabase? = null

    fun init(context: Context) {
        database = Room.databaseBuilder(context, TestAppDatabase::class.java, "review_db").fallbackToDestructiveMigration().build()
    }
}