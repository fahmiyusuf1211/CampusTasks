
package com.example.campustasks.domain

data class Task(
    override val id: String,
    val title: String,
    val description: String? = null,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val status: TaskStatus = TaskStatus.TODO,
    val estimatedMinutes: Int,
    val tags: Set<String> = emptySet()
) : Identifiable {


    fun normalizedTitle(): String = title.trim()

    fun normalizedDescription(): String? =
        description?.trim()?.takeIf { it.isNotEmpty() }

    fun normalizedTags(): Set<String> =
        tags.map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

    fun markInProgress(): Task {
        return copy(status = TaskStatus.IN_PROGRESS)
    }

    fun markDone(): Task {
        return copy(status = TaskStatus.DONE)
    }
}
