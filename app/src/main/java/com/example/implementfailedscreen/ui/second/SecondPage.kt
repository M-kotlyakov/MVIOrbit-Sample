package com.example.implementfailedscreen.ui.second

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.implementfailedscreen.R

@Composable
fun SecondPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.second),
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = stringResource(id = R.string.input_error_message)) },
                value = text,
                onValueChange = { newText -> text = newText }
            )
            Spacer(modifier = Modifier.height(20.dp))
            val stringRes = stringResource(id = R.string.failed)
            val enable = text.isNotEmpty()
            Button(
                enabled = enable,
                onClick = { navController.navigate(route = "$stringRes/$text") }
            ) {
                Text(text = stringResource(id = R.string.btn_to_failed))
            }
        }
    }
}