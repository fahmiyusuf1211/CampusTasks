
package com.example.campustasks.domain

object TaskValidator {

    fun validate(task: Task): Boolean {
        return task.id.length in 3..80 &&
                task.title.trim().length in 3..80 &&
                task.estimatedMinutes in 5..480 &&
                task.tags.none { it.isBlank() }
    }
}
