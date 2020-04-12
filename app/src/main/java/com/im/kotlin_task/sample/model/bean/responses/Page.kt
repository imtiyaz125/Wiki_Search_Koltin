package com.im.kotlin_task.sample.model.bean.responses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "wiki_pages")
class Page {
    @SerializedName("index")
    var index: Long? = null
    @SerializedName("ns")
    var ns: Long? = null
    @PrimaryKey
    @SerializedName("pageid")
    var pageid: Long? = null
   @Embedded
    @SerializedName("terms")
    var terms: Terms? = null
    @Embedded
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null
    @SerializedName("title")
    var title: String? = null

}
