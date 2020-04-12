package com.im.kotlin_task.sample.model.bean.responses

import com.google.gson.annotations.SerializedName

class Continue {

    @SerializedName("continue")
    var `continue`: String? = null
    @SerializedName("gpsoffset")
    var gpsoffset: Long? = null

}
