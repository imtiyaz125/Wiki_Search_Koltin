package com.im.kotlin_task.sample.view.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.im.kotlin_task.sample.R
import com.im.kotlin_task.sample.databinding.FragmentWebviewBinding
import com.im.kotlin_task.sample.view.base.BaseFragment
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.annotation.TargetApi
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.widget.Toast
import android.webkit.WebViewClient



class WebviewFragment : BaseFragment() {
    private lateinit var binding: FragmentWebviewBinding
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(context).apply {
            setMessage("Loading Please wait...")
        }
    }
    companion object {
        val TAG = WebviewFragment::class.java.canonicalName
        fun create(url:String): WebviewFragment {
            val bundle=Bundle()
            bundle.putString("url",url)
            return WebviewFragment().apply {
                arguments=bundle
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_webview
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {
        this.binding = binding as FragmentWebviewBinding
        loadWebView()
    }

    private fun loadWebView() {
        binding.detailWv.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                progressDialog.hide()
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog.hide()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog.show()
            }
        }

        binding.detailWv.loadUrl(arguments?.getString("url"))
    }

}