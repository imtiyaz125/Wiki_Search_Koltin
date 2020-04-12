package com.im.kotlin_task.sample.model.remote


import com.im.kotlin_task.sample.model.bean.responses.WikiSearchResponse
import retrofit2.Response
import retrofit2.http.*
interface ApiServices {

    @GET(ApiConstant.SEARCH)
    suspend fun getWikiSearch(@QueryMap params: HashMap<String, String>): Response<WikiSearchResponse>


}