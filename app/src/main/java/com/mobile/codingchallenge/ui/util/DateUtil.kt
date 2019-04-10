package com.mobile.codingchallenge.ui.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val DATE_FORMAT_FROM = "yyyy-MM-dd"
private const val DATE_FORMAT_TO = "dd MMM yyyy"

@Singleton
class DateUtil @Inject constructor() {

    val fromDateFormat = SimpleDateFormat(DATE_FORMAT_FROM)
    val toDateFormat = SimpleDateFormat(DATE_FORMAT_TO)

    fun todayDate(): String {
        return fromDateFormat.format(Date().time)
    }

}