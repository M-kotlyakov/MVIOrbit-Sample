package com.example.implementfailedscreen.ui.first

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
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
import com.example.implementfailedscreen.ui.common.UiStatus
import com.example.implementfailedscreen.ui.failed.FailedPage

@Composable
fun FirstPage(
    modifier: Modifier = Modifier,
    state: FirstState,
    navController: NavController,
    showText: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var serverText by remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when(state.status) {

                is UiStatus.Loading -> {
                    CircularProgressIndicator(modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                    )
                }

                is UiStatus.Failed -> {
                    FailedPage(navController = navController)
                }

                UiStatus.Success -> {
                    Text(
                        text = "st - ${state.st}",
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "digit - ${state.digit}",
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.first),
                        fontSize = 48.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        label = { Text(text = stringResource(id = R.string.input_text_to_server)) },
                        value = serverText,
                        onValueChange = { newServerText ->
                            serverText = newServerText
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        label = { Text(text = stringResource(id = R.string.input_error_message)) },
                        value = text,
                        onValueChange = { newText -> text = newText }
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    val stringRes = stringResource(id = R.string.failed)
                    val btnFailedEnable = text.isNotEmpty()
                    val btnServerEnable = serverText.isNotEmpty()

                    Button(
                        enabled = btnFailedEnable,
                        onClick = { navController.navigate(route = "$stringRes/$text") }
                    ) {
                        Text(text = stringResource(id = R.string.btn_to_failed))
                    }
                    Button(
                        enabled = btnServerEnable,
                        onClick = { showText(serverText) }
                    ) {
                        Text(text = stringResource(id = R.string.btn_to_server))
                    }
                }
            }
        }
    }
}