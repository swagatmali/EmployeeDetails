package com.example.employeedetails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedetails.model.EmployeeObject
import com.example.employeedetails.repository.EmployeeRepository
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
class EmployeeDetailsViewModel @Inject constructor(
    private val repository: EmployeeRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) :
    ViewModel() {

    private val _employeeUiState =
        MutableStateFlow<UiState<EmployeeObject>>(UiState.Loading)
    val employeeUiState: StateFlow<UiState<EmployeeObject>> = _employeeUiState
    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    fun startFetchingEmployee(empId: Int) {
        if (checkInternetConnection()) {
            fetchEmployee(empId)
        } else {
            _employeeUiState.value = UiState.Error("EmployeeObject Not found.")
        }
    }

    private fun fetchEmployee(empId: Int) {
        viewModelScope.launch(dispatcherProvider.main) {
            repository.getEmployee(empId)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _employeeUiState.value = UiState.Error(e.toString())
                }.collect {
                    _employeeUiState.value = UiState.Success(it)
                }
        }
    }
}