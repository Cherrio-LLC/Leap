package com.cherrio.leap.utils

import android.os.Build

object SDKChecks {
    val isLessThanApi29 = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
    fun isOrGreaterThanApi29() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}