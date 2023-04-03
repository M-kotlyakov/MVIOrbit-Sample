package com.example.implementfailedscreen.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.implementfailedscreen.R

sealed class BottomBarScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object First : BottomBarScreen(route = "first", R.string.first, Icons.Filled.Home)
    object Second: BottomBarScreen(route = "second", R.string.second, Icons.Filled.Favorite)
}

val items = listOf(
    BottomBarScreen.First,
    BottomBarScreen.Second
)