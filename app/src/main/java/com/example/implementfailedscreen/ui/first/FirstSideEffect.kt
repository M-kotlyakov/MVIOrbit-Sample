package com.example.implementfailedscreen.ui.first

sealed class FirstSideEffect {
    data class ShowServerMessage(val message: String) : FirstSideEffect()
}