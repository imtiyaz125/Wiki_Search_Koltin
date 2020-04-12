package com.im.kotlin_task.sample.view.home

import android.app.ProgressDialog
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.kotlin_task.sample.R
import com.im.kotlin_task.sample.databinding.FragmentSearchBinding
import com.im.kotlin_task.sample.model.bean.requests.GetWikiSearchRequest
import com.im.kotlin_task.sample.model.bean.responses.Page
import com.im.kotlin_task.sample.model.bean.responses.WikiSearchResponse
import com.im.kotlin_task.sample.model.remote.ApiResponse
import com.im.kotlin_task.sample.utils.Connectivity
import com.im.kotlin_task.sample.view.base.BaseFragment
import com.im.kotlin_task.sample.view.home.adapter.SearchAdapter
import com.im.kotlin_task.sample.view.home.adapter.iClickListener
import com.im.kotlin_task.sample.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(), iClickListener {
    override fun onItemClicked(pos: Int) {
        if (searchedList[pos].thumbnail != null)
            listener?.onPageItemClicked(searchedList[pos].thumbnail?.source!!)
        else
            Toast.makeText(
                context,
               getString(R.string.no_url_found),
                Toast.LENGTH_LONG
            )
                .show()


    }

    private var listener: iSearchFragmentInteractor?=null
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: SearchAdapter
    private var searchedList = ArrayList<Page>()
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }

    companion object {
        val TAG = SearchFragment::class.java.canonicalName
        fun create(
        ): SearchFragment {
            return SearchFragment()
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {
        this.binding = binding as FragmentSearchBinding
        initRecyclerView()
        setSearchListener()
    }

    private fun setSearchListener() {
        binding.searchEt.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    Log.e("after", "after")
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Log.e("after", "before")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.e("after", "onchange")
                    if (s.isNullOrBlank() || binding.searchEt.text.toString().trim().isBlank()) return

                    Handler().postDelayed({
                        hitWikiSearchApi(binding.searchEt.text.toString().trim())
                    }, 1000)

                }


            })
    }

    private val wikiResponseObserver: Observer<ApiResponse<WikiSearchResponse>> by lazy {
        Observer<ApiResponse<WikiSearchResponse>> {
            handleSearchReponse(it)
        }
    }

    private fun hitWikiSearchApi(queryString: String) {
        if (Connectivity.isConnected(context)) {
            /*** request viewModel to get data ***/
            viewModel.getWikiSearch(GetWikiSearchRequest().apply {
                gpssearch = queryString
            })
            /*** observe live data of viewModel*/
            viewModel.wikiResponse.observe(this, wikiResponseObserver)
        } else {
            Toast.makeText(
                context,
                resources.getString(R.string.no_network_error),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    /* Response Handlers */
    private fun handleSearchReponse(response: ApiResponse<WikiSearchResponse>) {
        when (response.status) {
            ApiResponse.Status.LOADING -> {
                progressDialog.show()
            }
            ApiResponse.Status.SUCCESS -> {
                progressDialog.dismiss()
                if (searchedList.isNotEmpty())
                    searchedList.clear()
                if (response.data?.query?.pages != null) {
                    if (response.data.query?.pages!!.isEmpty()) {
                        binding.noDataTv.visibility = VISIBLE
                    } else {
                        binding.noDataTv.visibility = GONE
                    }
                    searchedList.addAll(response.data.query?.pages!!)
                    binding.searchRv.recycledViewPool.clear()
                    adapter.notifyDataSetChanged()
                }
            }
            ApiResponse.Status.ERROR -> {
                progressDialog.dismiss()
                if (response.error?.code == 500)
                    Toast.makeText(context, response.error.message, Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(
                        context,
                        getString(R.string.internal_server_error),
                        Toast.LENGTH_LONG
                    ).show()

            }
        }
    }

    private fun initRecyclerView() {
        binding.searchRv.layoutManager = LinearLayoutManager(context)
        adapter = SearchAdapter(context!!, searchedList, this)
        binding.searchRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun setListner(listener:iSearchFragmentInteractor) {
        this.listener=listener
    }

    interface iSearchFragmentInteractor{
    fun onPageItemClicked(url:String)
}
}