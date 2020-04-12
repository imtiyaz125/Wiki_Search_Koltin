package com.im.kotlin_task.sample.model.bean.responses

import com.google.gson.annotations.SerializedName

class WikiSearchResponse {

    @SerializedName("batchcomplete")
    var batchcomplete: Boolean? = null
    @SerializedName("continue")
    var `continue`: Continue? = null
    @SerializedName("query")
    var query: Query? = null

}
