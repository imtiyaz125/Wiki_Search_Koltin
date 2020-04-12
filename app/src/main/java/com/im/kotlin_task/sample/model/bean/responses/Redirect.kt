package com.im.kotlin_task.sample.model.bean.responses

import com.google.gson.annotations.SerializedName

class Redirect {

    @SerializedName("from")
    var from: String? = null
    @SerializedName("index")
    var index: Long? = null
    @SerializedName("to")
    var to: String? = null

}
