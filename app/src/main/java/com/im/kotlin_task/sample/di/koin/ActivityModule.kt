package com.im.kotlin_task.sample.di.koin

import com.im.kotlin_task.sample.view.home.HomeActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

var homeActivityModule = module {

    scope(named<HomeActivity>()) {
    }
}