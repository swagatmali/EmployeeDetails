package com.example.employeedetails.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.employeedetails.model.EmployeeListObject
import com.example.employeedetails.ui.UiState
import com.example.employeedetails.ui.base.ShowError
import com.example.employeedetails.ui.base.ShowLoading
import com.example.employeedetails.viewmodels.EmployeeListViewModel

@Composable
fun EmployeeListScreenRoute(
    onNewsClick: (url: Int) -> Unit,
    employeeViewModel: EmployeeListViewModel = hiltViewModel()
) {
    val topHeadlineUiState: UiState<List<EmployeeListObject>> by employeeViewModel.employeeListUiState.collectAsStateWithLifecycle()
    EmployeeListScreen(uiState = topHeadlineUiState, onNewsClick, onRetryClick = {
        employeeViewModel.startFetchingEmployeeList()
    })
}

@Composable
fun EmployeeListScreen(
    uiState: UiState<List<EmployeeListObject>>,
    onNewsClick: (url: Int) -> Unit,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            EmployeeList(uiState.data, onNewsClick)
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
fun EmployeeList(articles: List<EmployeeListObject>, onNewsClick: (url: Int) -> Unit) {
    LazyColumn {
        items(articles.size) { index ->
            EmployeeListItem(articles[index], onNewsClick = onNewsClick)
        }
    }
}

@Composable
fun EmployeeListItem(employeeListObject: EmployeeListObject, onNewsClick: (url: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp)
            .clickable { employeeListObject.id?.let { onNewsClick(it) } },
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
                    text = "ID : ${employeeListObject.id}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Name : ${employeeListObject.first_name}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


