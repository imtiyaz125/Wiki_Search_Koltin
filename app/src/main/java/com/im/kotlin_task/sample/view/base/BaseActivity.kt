package com.im.kotlin_task.sample.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.im.kotlin_task.sample.model.local.preference.PreferenceConstants
import com.im.kotlin_task.sample.model.local.preference.PreferenceHelper
import com.im.kotlin_task.sample.view_model.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


abstract class BaseActivity : AppCompatActivity(), BaseInterFace, LifecycleOwner {

    private val baseViewModel: BaseViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutIdRes = layout
        baseViewModel.setFirstLaunch()
        if (layoutIdRes != 0) {
            val binding = DataBindingUtil.setContentView(this, layoutIdRes) as ViewDataBinding
            initUI(binding)
        }
    }
}
