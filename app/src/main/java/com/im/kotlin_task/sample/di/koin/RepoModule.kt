package com.im.kotlin_task.sample.di.koin

import com.im.kotlin_task.sample.model.repo.AppRepository
import com.im.kotlin_task.sample.model.repo.HomeRepository
import org.koin.dsl.module




val repoModule = module {
    single { HomeRepository(get(), get(), get()) }
    single { AppRepository(get()) }

}