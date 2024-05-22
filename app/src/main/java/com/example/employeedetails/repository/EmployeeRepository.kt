package com.example.employeedetails.repository

import com.example.employeedetails.api.EmployeeAPI
import com.example.employeedetails.model.EmployeeObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeAPI: EmployeeAPI,
) {
    suspend fun getEmployee(empId: Int): Flow<EmployeeObject> {
        return flow { emit(employeeAPI.getEmployee(empId)) }
            .map {
                it.employeeObject
            }
    }
}