package com.im.kotlin_task.sample.model.remote

import com.google.gson.Gson
import java.util.*


class ReflectionUtil(private val gson: Gson) {
    fun convertPojoToMap(model: Any): java.util.HashMap<String, String> {
        val json = gson.toJson(model)
        return gson.fromJson<java.util.HashMap<String, String>>(json, HashMap::class.java)
    }
}