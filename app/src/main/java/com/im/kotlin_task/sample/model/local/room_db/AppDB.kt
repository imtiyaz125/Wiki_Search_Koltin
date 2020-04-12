package com.im.kotlin_task.sample.model.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.im.kotlin_task.sample.model.bean.responses.Page
import com.im.kotlin_task.sample.model.local.room_db.dao.SearchDao

@Database(entities = [Page::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDB : RoomDatabase() {

    abstract fun searchDao(): SearchDao


}