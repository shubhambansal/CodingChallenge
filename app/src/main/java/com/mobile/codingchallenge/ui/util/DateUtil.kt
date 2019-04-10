package com.mobile.codingchallenge.ui.util

import java.text.SimpleDateFormat


class DateUtil {

    companion object {
        private const val DATE_FORMAT_FROM = "yyyy-MM-dd"
        private const val DATE_FORMAT_TO = "dd MMM yyyy"

        val fromDateFormat = SimpleDateFormat(DATE_FORMAT_FROM)
        val toDateFormat = SimpleDateFormat(DATE_FORMAT_TO)
    }
}