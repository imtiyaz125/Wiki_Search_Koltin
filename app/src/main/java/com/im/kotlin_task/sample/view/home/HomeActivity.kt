package com.im.kotlin_task.sample.view.home


import androidx.databinding.ViewDataBinding
import com.im.kotlin_task.sample.R
import com.im.kotlin_task.sample.databinding.ActivityHomeBinding
import com.im.kotlin_task.sample.view.base.BaseActivity
import com.im.kotlin_task.sample.view.detail.WebviewFragment

class HomeActivity : BaseActivity(), SearchFragment.iSearchFragmentInteractor {


    private lateinit var binding: ActivityHomeBinding
    private var detailFrag:WebviewFragment?=null

    override fun getLayout(): Int {
        return R.layout.activity_home
    }

    override fun initUI(binding: ViewDataBinding?) {
        this.binding = binding as ActivityHomeBinding
        loadHomeFragment()
    }

    private fun loadHomeFragment() {
        val frag = SearchFragment.create()
        frag.setListner(this)
        supportFragmentManager.beginTransaction().replace(R.id.containerFl, frag,SearchFragment.TAG).commit()
    }
    private fun loadDetailFragment(url:String) {
        detailFrag = WebviewFragment.create(url)
        supportFragmentManager.beginTransaction().add(R.id.containerFl, detailFrag!!,WebviewFragment.TAG).addToBackStack(WebviewFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1) {
            val trans = supportFragmentManager.beginTransaction()
            detailFrag?.let { trans.remove(it) }
            trans.commit()
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }

    override fun onPageItemClicked(url:String) {
        loadDetailFragment(url)
    }

}