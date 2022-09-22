package com.wantech.smartCopier.feature_auth.presentation.signUp.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wantech.smartCopier.R
import com.wantech.smartCopier.feature_auth.presentation.login.components.AButton
import com.wantech.smartCopier.feature_auth.presentation.login.components.InputTextField

@Composable

fun SignUpTextFields(
    buttonLabel: String,
    signUpFinally: () -> Unit,
    onClickToLogin: () -> Unit
) {
    var emailFieldState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var userNameFieldState by remember {
        mutableStateOf("")
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                InputTextField(
                    textValue = emailFieldState,
                    labelText = "Email",
                    tittle = "Example@gmail.com",
                    trailingIcon = Icons.Default.Email,
                    onValueChange = { emailFieldState = it },
                    first = true
                )
                InputTextField(
                    textValue = userNameFieldState,
                    labelText = "UserName",
                    tittle = "e.g Joh",
                    trailingIcon = Icons.Default.Person,
                    onValueChange = { userNameFieldState = it },
                    first = true
                )
                InputTextField(
                    textValue = passwordState,
                    labelText = "Password",
                    tittle = "Enter your password",
                    trailingIconResource = R.drawable.ic_eye,
                    onValueChange = { passwordState = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    )
                )

                AButton(
                    text = buttonLabel,
                    onClick = signUpFinally,
                    modifier = Modifier,
                    buttonEnabled = { passwordState.isNotBlank() && (passwordState.length >= 6) }
                )
                TextButton(
                    onClick = onClickToLogin,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Already Have an Account?",
                        color = MaterialTheme.colors.surface,
//                    modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "SIgn In",
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier
//                        .fillMaxWidth(0.7f)
                            .padding(4.dp)
                    )
                }
            }
        }
    }


}