package com.example.implementfailedscreen.ui.failed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.implementfailedscreen.R
import com.example.implementfailedscreen.ui.common.UiStatus

@Composable
fun FailedPage(
    navController: NavController,
    errorMessage: String? = "",
    block: @Composable (String?) -> Unit = { BaseBlock(navController, errorMessage) }
) {
    block(errorMessage)
}

@Composable
fun BaseBlock(
    navController: NavController,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "Error"
        )
        Spacer(modifier = Modifier.height(20.dp))
        errorMessage?.let {
            Text(
                text = it,
                fontSize = 48.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Ok")
        }
    }
}
