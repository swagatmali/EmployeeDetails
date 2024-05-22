package com.example.employeedetails.model

import com.google.gson.annotations.SerializedName

data class EmployeeListResponse(
    @SerializedName("data")  val employeeListObjects: List<EmployeeListObject>
)