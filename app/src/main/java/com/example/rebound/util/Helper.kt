package com.example.rebound.util

import android.content.res.Resources
import android.text.format.DateFormat
import java.util.*

object Helper {

    fun prettyPrintDate(currentDate: Date, wantedDate: Date): CharSequence? {
        val dayInMillis = 24 * 3600000
        val twoDaysInMillis = 2 * dayInMillis
        val timeDifference = currentDate.time - wantedDate.time

        return when {
            timeDifference < dayInMillis -> DateFormat.format("hh:mm a", wantedDate)
            timeDifference < twoDaysInMillis -> "Yesterday"
            else -> DateFormat.format("dd/MM/yyyy", wantedDate)
        }
    }

    fun dpToPixels(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()
}