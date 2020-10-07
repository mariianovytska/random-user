package com.novytska.randomuser.presentation.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class DateFormatter {

    fun convertFormat(
        source: String,
        converted: String = YYYYHMMHddTZ,
        formatted: String = YYYYHMMHdd
    ): String? {
        val sdf = SimpleDateFormat(converted, Locale.ROOT)
        val convertedDate: Date?
        var formattedDate: String? = null
        try {
            convertedDate = sdf.parse(source)
            formattedDate =
                SimpleDateFormat(formatted, Locale.ROOT).format(
                    convertedDate
                )
        } catch (e: ParseException) {
            Log.i(TAG,"Failed to convert ISOTimeToDate: " + e.localizedMessage)
        }
        return formattedDate
    }

    companion object {
        private const val YYYYHMMHddTZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val YYYYHMMHdd = "yyyy-MM-dd"
        private val TAG = DateFormatter::class.java.simpleName
    }
}