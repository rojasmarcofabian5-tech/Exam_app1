package com.example.exam_app1 // Asegúrate que el package sea el tuyo

// TODOS LOS IMPORTS NECESARIOS
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exam_app1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen( // Nota el cambio de nombre, ahora coincide con tu archivo
    onSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmError by remember { mutableStateOf(false) }
    var confirmErrorText by remember { mutableStateOf("Required") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Create your account",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Signup",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF555555),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Name") },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = brightBlue,
                unfocusedBorderColor = lightGrayBorder,
                cursorColor = brightBlue
            ),
            singleLine = true,
            isError = nameError,
            supportingText = {
                if (nameError) {
                    Text(text = "El nombre es requerido", color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = brightBlue,
                unfocusedBorderColor = lightGrayBorder,
                cursorColor = brightBlue
            ),
            singleLine = true,
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(text = "Email is required", color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = brightBlue,
                unfocusedBorderColor = lightGrayBorder,
                cursorColor = brightBlue
            ),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, "Toggle password visibility")
                }
            },
            isError = passwordError,
            supportingText = {
                if (passwordError) {
                    Text(text = "Password is required", color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmError = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirm Password") },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = brightBlue,
                unfocusedBorderColor = lightGrayBorder,
                cursorColor = brightBlue
            ),
            singleLine = true,
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, "Toggle password visibility")
                }
            },
            isError = confirmError,
            supportingText = {
                if (confirmError) {
                    Text(text = confirmErrorText, color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                nameError = name.isBlank()
                emailError = email.isBlank()
                passwordError = password.isBlank()

                if (confirmPassword.isBlank()) {
                    confirmError = true
                    confirmErrorText = "Required"
                } else if (password != confirmPassword) {
                    confirmError = true
                    confirmErrorText = "Passwords do not match"
                } else {
                    confirmError = false
                }

                if (!nameError && !emailError && !passwordError && !confirmError) {
                    onSignUpClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = brightBlue)
        ) {
            Text("Signup", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(modifier = Modifier.weight(1f), color = lightGrayBorder, thickness = 1.dp)
            Text("Or", color = Color.Gray, modifier = Modifier.padding(horizontal = 16.dp), fontSize = 14.sp)
            Divider(modifier = Modifier.weight(1f), color = lightGrayBorder, thickness = 1.dp)
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = lightGrayBg,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.f), contentDescription = "Facebook Logo", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Login with Facebook", fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, lightGrayBorder),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.g), contentDescription = "Google Logo", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Login with Google", fontWeight = FontWeight.Medium)
            }
        }

        Row(
            modifier = Modifier.padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", color = Color.Gray)
            TextButton(
                onClick = onLoginClick,
                contentPadding = PaddingValues(start = 4.dp)
            ) {
                Text("Login", color = brightBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() { // Preview de la pantalla
    Surface {
        SignUpScreen()
    }
}