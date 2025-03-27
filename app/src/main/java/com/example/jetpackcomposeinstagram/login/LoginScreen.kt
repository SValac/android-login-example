package com.example.jetpackcomposeinstagram.login

import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeinstagram.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogin() {
    LoginScreen(loginViewModel = LoginViewModel() ,modifier = Modifier.padding(top = 30.dp))
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(modifier = Modifier.align(Alignment.TopEnd))
        Body(loginViewModel =  loginViewModel, modifier = Modifier.align(Alignment.Center))
        Footer(modifier = Modifier.align((Alignment.BottomCenter)))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier) {
        HorizontalDivider()
        SingUp()
    }
}

@Composable
fun SingUp() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have an account?",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(Modifier.padding(horizontal = 4.dp))
        Text(
            text = "Sing Up",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Body(loginViewModel: LoginViewModel, modifier: Modifier) {
    val email:String by loginViewModel.email.collectAsState()
    val password: String by loginViewModel.password.collectAsState()
    val isLogginEnable by loginViewModel.isLoginEnabled.collectAsState()
    Column(modifier = modifier) {
        ImageLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email = email) {
            loginViewModel.onLoginChange(email = it, password =  password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password = password, onTextChange = {
           loginViewModel.onLoginChange(email = email, password = it)
        })
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isEnable = isLogginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()

    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.fb),
            contentDescription = "Social Login Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            "Continue as Valac Shiro",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff4EA8E9),

            )
    }
}

@Composable
fun LoginDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f))

        Text(
            text = "OR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color.LightGray

        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

@Composable
fun LoginButton(isEnable: Boolean) {
    var activity = LocalActivity.current
    Button(
        onClick = { Toast.makeText(activity, "Loggin clieckd", Toast.LENGTH_LONG).show() },
        enabled = isEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text("Login")
    }
}



@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        "Forgot Password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var showPassword by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val imagen = if (showPassword) {
                painterResource(R.drawable.baseline_visibility_off_24)
            } else {
                painterResource(R.drawable.baseline_visibility_24)
            }
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(painter = imagen, contentDescription = "Show Password")
            }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.insta),
        contentDescription = "Instagram Logo",
        modifier = modifier
    )

}

@Composable
fun Header(modifier: Modifier) {
    // ger current contest as activity to
    val activity = LocalActivity.current
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier.clickable {
            activity?.finish()
        })
}
