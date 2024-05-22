package com.example.employeedetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.employeedetails.ui.base.AppNavHost
import com.example.employeedetails.ui.theme.EmployeeDetailsTheme
import com.example.employeedetails.util.AppConstant
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeDetailsTheme {
                Scaffold(topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    ), title = { Text(text = AppConstant.APP_NAME) })
                }) {
                    Box(modifier = Modifier.padding(it)) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}

