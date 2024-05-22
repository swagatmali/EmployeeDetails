package com.example.employeedetails.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.employeedetails.model.EmployeeObject
import com.example.employeedetails.ui.UiState
import com.example.employeedetails.ui.base.ShowError
import com.example.employeedetails.ui.base.ShowLoading
import com.example.employeedetails.viewmodels.EmployeeDetailsViewModel

@Composable
fun EmployeeDetailsRoute(
    empId: Int,
    employeeDetailsViewModel: EmployeeDetailsViewModel = hiltViewModel()
) {
    val employeeDetailsUiState: UiState<EmployeeObject> by employeeDetailsViewModel.employeeUiState.collectAsStateWithLifecycle()
    employeeDetailsViewModel.startFetchingEmployee(empId)
    EmployeeDetailsScreen(uiState = employeeDetailsUiState, onRetryClick = {
        employeeDetailsViewModel.startFetchingEmployee(empId)
    })
}

@Composable
fun EmployeeDetailsScreen(
    uiState: UiState<EmployeeObject>,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            EmployeeDetails(uiState.data)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message) {
                onRetryClick()
            }
        }
    }
}

@Composable
fun EmployeeDetails(employeeObject: EmployeeObject) {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = "ID : ${employeeObject.id}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Email : ${employeeObject.email}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Fist Name : ${employeeObject.first_name}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Last Name : ${employeeObject.last_name}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
