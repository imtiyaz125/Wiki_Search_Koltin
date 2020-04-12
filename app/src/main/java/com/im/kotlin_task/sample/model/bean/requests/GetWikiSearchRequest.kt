package com.im.kotlin_task.sample.model.bean.requests

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GetWikiSearchRequest : Serializable {

    @SerializedName("action")
    var action: String = "query"
    @SerializedName("format")
    var format: String = "json"

    @SerializedName("prop")
    var prop: String = "pageimages|pageterms"

    @SerializedName("generator")
    var generator: String = "prefixsearch"
    @SerializedName("redirects")
    var redirects = "1"
    @SerializedName("formatversion")
    var formatversion = "2"
    @SerializedName("piprop")
    var piprop: String = "thumbnail"
    @SerializedName("pithumbsize")
    var pithumbsize = "50"
    @SerializedName("pilimit")
    var pilimit = "10"
    @SerializedName("wbptterms")
    var wbptterms: String = "description"
    @SerializedName("gpssearch")
    var gpssearch: String = ""
    @SerializedName("gpslimit")
    var gpslimit = "10"
}