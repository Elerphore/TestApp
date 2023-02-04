package ru.elerphore.testapplication.db.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReviewDBEntity::class], version = 1)
abstract class ReviewRepository : RoomDatabase() {

    abstract fun reviewDao() : ReviewDao

}