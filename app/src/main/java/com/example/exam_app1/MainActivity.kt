package com.example.exam_app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exam_app1.ui.theme.Exam_app1Theme
// Asegúrate que los imports apunten a tus archivos de pantalla
import com.example.exam_app1.LoginScreen
import com.example.exam_app1.SignUpScreen
import com.example.exam_app1.MainScreen // <-- NUEVO IMPORT

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exam_app1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onLoginClick = {
                    // NAVEGA A MAIN Y LIMPIA LA PILA
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onSignupClick = {
                    navController.navigate("signup")
                },
                onForgotPasswordClick = {
                    // TODO: Lógica de olvidar contraseña
                }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUpClick = {
                    // NAVEGA A MAIN Y LIMPIA LA PILA
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true } // Limpia hasta el login
                    }
                },
                onLoginClick = {
                    navController.popBackStack() // Regresa a Login
                }
            )
        }

        // --- NUEVA RUTA ---
        composable("main") {
            MainScreen()
        }
    }
}