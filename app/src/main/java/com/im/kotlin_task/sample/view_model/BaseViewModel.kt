package com.im.kotlin_task.sample.view_model


import androidx.lifecycle.ViewModel
import com.im.kotlin_task.sample.model.repo.AppRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


open class BaseViewModel : ViewModel(),KoinComponent {

    val appRepo: AppRepository by inject()

    fun setFirstLaunch(){
        appRepo.setIsAppNotFirstTimeLaunched()
    }
}