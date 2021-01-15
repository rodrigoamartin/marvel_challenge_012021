package com.rma.marvelchallenge.core.platform

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private var TAG : String = DateUtils::class.java.name

    fun toDate(date: String = ""): Date?{
        if (date.isEmpty()) return null
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.getDefault())
            sdf.parse(date)
        } catch (e: Exception) {
            Log.e(TAG, "toDate: error parsing date", e)
            null
        }
    }

    fun toString(date: Date?): String {
        if (date == null) return ""
        return try {
            DateFormat.getDateInstance(DateFormat.LONG).format(date)
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "toString: error parsing date", e)
            ""
        }
    }
}