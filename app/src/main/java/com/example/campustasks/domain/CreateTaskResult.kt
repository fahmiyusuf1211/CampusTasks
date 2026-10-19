
package com.example.campustasks.domain

sealed class CreateTaskResult {

    data class Success(val task: Task) : CreateTaskResult()

    data class Invalid(val message: String) : CreateTaskResult()
}
