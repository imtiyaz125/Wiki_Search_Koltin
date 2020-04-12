package com.im.kotlin_task.sample.model.repo

import com.im.kotlin_task.sample.model.local.preference.PreferenceConstants
import com.im.kotlin_task.sample.model.local.preference.PreferenceHelper


class AppRepository(private val preferences: PreferenceHelper) {

    fun getIsAppFirstTimeLaunched(): Boolean {
        return preferences.getBoolean(PreferenceConstants.IS_NOT_FIRST_TIME_LAUNCHED)
    }
    fun setIsAppNotFirstTimeLaunched() {
        return preferences.put(PreferenceConstants.IS_NOT_FIRST_TIME_LAUNCHED,true)
    }
}