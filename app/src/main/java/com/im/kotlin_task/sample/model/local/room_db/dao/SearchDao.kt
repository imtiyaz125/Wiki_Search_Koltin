package com.im.kotlin_task.sample.model.local.room_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.im.kotlin_task.sample.model.bean.responses.Page

@Dao
interface SearchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPages(pages: List<Page>)


    @Query("SELECT * FROM wiki_pages")
    fun retrieveAllPages(): List<Page>

}
