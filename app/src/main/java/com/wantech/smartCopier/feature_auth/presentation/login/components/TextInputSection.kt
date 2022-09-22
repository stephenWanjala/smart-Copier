package com.wantech.smartCopier.feature_auth.presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable

fun TextInPutSection(
    buttonLabel: String,
    onClickLoginButton: () -> Unit,
    onClickToSignUp: () -> Unit,
    onForgetPassword: () -> Unit
) {
    var emailFieldState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
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
                var passwordVisibility: Boolean by remember { mutableStateOf(false) }
//               TODO("Fix toggle password Icon and text transform")
                InputTextField(
                    textValue = passwordState,
                    labelText = "Password",
                    tittle = "Your Password",
                    trailingIcon = TogglePasswordVisibility(isVisible =passwordVisibility ){ changeParam(passwordVisibility)}  ,
                    trailingIconResource = null,
                    onValueChange = { passwordState = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation('*')
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val checkedT = remember {
                        mutableStateOf(false)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Checkbox(
                        checked = checkedT.value, onCheckedChange = {
                            checkedT.value = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colors.primary,
                            uncheckedColor = MaterialTheme.colors.surface,
//                    checkmarkColor = Color.Magenta
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Remember Me",
                        style = MaterialTheme.typography.caption
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = onForgetPassword,
                        modifier = Modifier.wrapContentHeight(),
                        contentPadding = PaddingValues(1.dp),
                    ) {
                        Text(
                            text = "Forgot password?",
                            color = MaterialTheme.colors.surface,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
                AButton(
                    text = buttonLabel,
                    onClick = onClickLoginButton,
                    modifier = Modifier,
                    buttonEnabled =
                    {
                        passwordState.isNotBlank() &&
                                ((passwordState.length >= 8)
                                        && emailFieldState.isNotBlank()
                                        && android.util.Patterns.EMAIL_ADDRESS.matcher(
                                    emailFieldState
                                ).matches())
                    }

                )

                TextButton(
                    onClick = onClickToSignUp,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Text(
                        text = "Don't Have Account?",
                        color = MaterialTheme.colors.surface,
//                    modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
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


@Composable
fun TogglePasswordVisibility(isVisible: Boolean,changeMe:()->Boolean): ImageVector {
    var bool:Boolean=isVisible
    var icon:ImageVector =Icons.Default.Visibility
    IconButton(onClick = {
        bool=!bool

    }) {
        icon = if (bool){
            Icons.Default.Visibility
        } else{
            Icons.Default.VisibilityOff
        }
    }
 return  icon
}
fun changeParam(bool: Boolean):Boolean =!bool