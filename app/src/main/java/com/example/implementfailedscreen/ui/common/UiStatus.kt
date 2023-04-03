package com.example.implementfailedscreen.ui.common

sealed class UiStatus {
    object Success : UiStatus()
    object Loading : UiStatus()
    data class Failed(val message: String = "") : UiStatus()
}