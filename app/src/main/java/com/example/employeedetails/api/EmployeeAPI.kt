package com.example.employeedetails.api

import com.example.employeedetails.model.EmployeeListResponse
import com.example.employeedetails.model.EmployeeObjectResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeAPI {

   @GET("users")
   suspend fun getEmployeeList(): EmployeeListResponse

   @GET("users/{id}")
   suspend fun getEmployee(@Path("id") id: Int): EmployeeObjectResponse

}