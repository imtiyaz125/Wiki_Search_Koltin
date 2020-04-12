package com.im.kotlin_task.sample.model.bean.responses

import com.google.gson.annotations.SerializedName

class Query {

    @SerializedName("pages")
    var pages: List<Page>? = null
    @SerializedName("redirects")
    var redirects: List<Redirect>? = null

}
