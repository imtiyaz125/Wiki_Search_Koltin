package com.im.kotlin_task.sample.di.koin

import androidx.room.Room
import com.im.kotlin_task.sample.MyApplication
import com.im.kotlin_task.sample.model.local.room_db.AppDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
var databaseModule = module {

    /** DB Singleton Provider */
    single {
        Room.databaseBuilder((androidApplication() as MyApplication), AppDB::class.java, "wiki.db")
            .build()
    }
    single { (get() as AppDB).searchDao() }

}