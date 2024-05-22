package com.example.employeedetails.model

import com.google.gson.annotations.SerializedName

data class EmployeeObjectResponse(
    @SerializedName("data") val employeeObject: EmployeeObject,
)