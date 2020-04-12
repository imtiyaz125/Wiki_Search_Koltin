package com.im.kotlin_task.sample.model.local.room_db

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun gettingListFromString(genreIds: String): List<String> {
        val list = ArrayList<String>()

        val array = genreIds.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (s in array) {
            if (!s.isEmpty()) {
                list.add(s)
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<String>): String {
        var genreIds = ""
        for (i in list) {
            genreIds += ",$i"
        }
        return genreIds
    }
}