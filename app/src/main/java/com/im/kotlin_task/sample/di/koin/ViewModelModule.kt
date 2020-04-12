package com.im.kotlin_task.sample.di.koin


import com.im.kotlin_task.sample.view_model.BaseViewModel
import com.im.kotlin_task.sample.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { HomeViewModel(get()) }
}