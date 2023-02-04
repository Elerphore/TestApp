package ru.elerphore.testapplication.db.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewDao {
    @Query("select * from Review")
    fun all() : LiveData<List<ReviewDBEntity>>

    @Insert
    fun insertAll(reviews: List<ReviewDBEntity>)

    @Query("delete from Review")
    fun deleteAll()
}