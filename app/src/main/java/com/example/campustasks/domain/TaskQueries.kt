
package com.example.campustasks.domain

object TaskQueries {

    fun openTasks(tasks: List<Task>): List<Task> {
        return tasks.filter { it.status != TaskStatus.DONE }
    }

    fun tasksByPriority(
        tasks: List<Task>,
        priority: TaskPriority
    ): List<Task> {
        return tasks.filter { it.priority == priority }
    }

    fun searchByKeyword(
        tasks: List<Task>,
        keyword: String
    ): List<Task> {
        return tasks.filter {
            it.title.contains(keyword, ignoreCase = true) ||
                    (it.description?.contains(
                        keyword,
                        ignoreCase = true
                    ) == true)
        }
    }

    fun groupByStatus(
        tasks: List<Task>
    ): Map<TaskStatus, List<Task>> {
        return tasks.groupBy { it.status }
    }

    fun <T : Identifiable> findById(
        items: List<T>,
        id: String
    ): T? {
        return items.find { it.id == id }
    }
}
