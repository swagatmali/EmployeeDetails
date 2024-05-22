package com.example.employeedetails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedetails.model.EmployeeListObject
import com.example.employeedetails.repository.EmployeeListRepository
import com.example.employeedetails.ui.UiState
import com.example.employeedetails.util.DispatcherProvider
import com.example.employeedetails.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val repository: EmployeeListRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val _employeeListUiState =
        MutableStateFlow<UiState<List<EmployeeListObject>>>(UiState.Loading)

    val employeeListUiState: StateFlow<UiState<List<EmployeeListObject>>> = _employeeListUiState

    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    init {
        startFetchingEmployeeList()
    }

    fun startFetchingEmployeeList() {
        if (checkInternetConnection()) {
            fetchEmployees()
        } else {
            _employeeListUiState.value = UiState.Error("EmployeeObject Not found.")
        }
    }

    private fun fetchEmployees() {
        viewModelScope.launch(dispatcherProvider.main) {
            repository.getEmployeeList()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _employeeListUiState.value = UiState.Error(e.toString())
                }.collect {
                    _employeeListUiState.value = UiState.Success(it)
                }
        }
    }
}