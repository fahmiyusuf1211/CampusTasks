
package com.example.campustasks.domain

object TaskFactory {

    fun create(
        id: String,
        title: String,
        description: String? = null,
        priority: TaskPriority = TaskPriority.MEDIUM,
        estimatedMinutes: Int,
        tags: Set<String> = emptySet()
    ): CreateTaskResult {

        val normalizedId = id.trim()
        val normalizedTitle = title.trim()
        val normalizedDescription =
            description?.trim()?.takeIf { it.isNotEmpty() }
        val normalizedTags = tags
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

        if (normalizedId.length !in 3..80) {
            return CreateTaskResult.Invalid(
                "ID harus terdiri dari 3 sampai 80 karakter"
            )
        }

        if (normalizedTitle.length !in 3..80) {
            return CreateTaskResult.Invalid(
                "Judul harus terdiri dari 3 sampai 80 karakter"
            )
        }

        if (estimatedMinutes !in 5..480) {
            return CreateTaskResult.Invalid(
                "Estimasi waktu harus antara 5 sampai 480 menit"
            )
        }

        val task = Task(
            id = normalizedId,
            title = normalizedTitle,
            description = normalizedDescription,
            priority = priority,
            estimatedMinutes = estimatedMinutes,
            tags = normalizedTags
        )

        return CreateTaskResult.Success(task)
    }
}
