package com.example.employeedetails.repository

import com.example.employeedetails.api.EmployeeAPI
import com.example.employeedetails.model.EmployeeListObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmployeeListRepository @Inject constructor(
    private val employeeAPI: EmployeeAPI,
    ) {

    suspend fun getEmployeeList(): Flow<List<EmployeeListObject>> {
        return flow { emit(employeeAPI.getEmployeeList()) }
            .map {
                it.employeeListObjects
            }
    }
}