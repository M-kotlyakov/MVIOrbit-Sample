package com.example.implementfailedscreen.ui.first

import com.example.implementfailedscreen.ui.common.UiStatus

data class FirstState(
    val status: UiStatus = UiStatus.Loading,
    val st: String = "",
    val digit: Int = 0
)