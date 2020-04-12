package com.im.kotlin_task.sample.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.im.kotlin_task.sample.model.bean.requests.GetWikiSearchRequest
import com.im.kotlin_task.sample.model.bean.responses.WikiSearchResponse
import com.im.kotlin_task.sample.model.remote.ApiResponse
import com.im.kotlin_task.sample.model.repo.HomeRepository


class HomeViewModel constructor(private val contactRepository: HomeRepository) :
    BaseViewModel() {

    private val _wikiResponse by lazy {
        MutableLiveData<ApiResponse<WikiSearchResponse>>()
    }

    val wikiResponse: LiveData<ApiResponse<WikiSearchResponse>> = _wikiResponse

    fun getWikiSearch(request: GetWikiSearchRequest) {
        contactRepository.getWikiSearchedPages(request, _wikiResponse)
    }

}