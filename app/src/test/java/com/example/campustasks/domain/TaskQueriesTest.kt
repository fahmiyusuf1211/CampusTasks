
package com.example.campustasks.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class TaskQueriesTest {

    @Test
    fun openTasksHanyaMengambilTugasYangBelumSelesai() {
        val tasks = listOf(
            Task(
                id = "T001",
                title = "Tugas pertama",
                estimatedMinutes = 30,
                status = TaskStatus.TODO
            ),
            Task(
                id = "T002",
                title = "Tugas kedua",
                estimatedMinutes = 45,
                status = TaskStatus.IN_PROGRESS
            ),
            Task(
                id = "T003",
                title = "Tugas ketiga",
                estimatedMinutes = 60,
                status = TaskStatus.DONE
            )
        )

        val result = TaskQueries.openTasks(tasks)

        assertEquals(2, result.size)
        assertEquals(
            listOf("T001", "T002"),
            result.map { it.id }
        )
    }


    @Test
    fun tasksByPriorityHanyaMengambilPrioritasYangSesuai() {
        val tasks = listOf(
            Task(
                id = "T001",
                title = "Tugas biasa",
                estimatedMinutes = 30,
                priority = TaskPriority.LOW
            ),
            Task(
                id = "T002",
                title = "Tugas penting",
                estimatedMinutes = 45,
                priority = TaskPriority.HIGH
            ),
            Task(
                id = "T003",
                title = "Tugas sangat penting",
                estimatedMinutes = 60,
                priority = TaskPriority.HIGH
            )
        )

        val result = TaskQueries.tasksByPriority(
            tasks,
            TaskPriority.HIGH
        )

        assertEquals(2, result.size)
        assertEquals(
            listOf("T002", "T003"),
            result.map { it.id }
        )
    }


    @Test
    fun searchByKeywordMencariJudulDanDeskripsi() {
        val tasks = listOf(
            Task(
                id = "T001",
                title = "Belajar Kotlin",
                description = "Mempelajari dasar pemrograman",
                estimatedMinutes = 30
            ),
            Task(
                id = "T002",
                title = "Membuat laporan",
                description = "Laporan praktikum Kotlin",
                estimatedMinutes = 45
            ),
            Task(
                id = "T003",
                title = "Belajar Java",
                description = "Mempelajari bahasa Java",
                estimatedMinutes = 60
            )
        )

        val result = TaskQueries.searchByKeyword(tasks, "kotlin")

        assertEquals(2, result.size)
        assertEquals(
            listOf("T001", "T002"),
            result.map { it.id }
        )
    }


    @Test
    fun groupByStatusMengelompokkanTugasDenganBenar() {
        val tasks = listOf(
            Task(
                id = "T001",
                title = "Tugas pertama",
                estimatedMinutes = 30,
                status = TaskStatus.TODO
            ),
            Task(
                id = "T002",
                title = "Tugas kedua",
                estimatedMinutes = 45,
                status = TaskStatus.DONE
            ),
            Task(
                id = "T003",
                title = "Tugas ketiga",
                estimatedMinutes = 60,
                status = TaskStatus.TODO
            )
        )

        val result = TaskQueries.groupByStatus(tasks)

        assertEquals(2, result[TaskStatus.TODO]?.size)
        assertEquals(1, result[TaskStatus.DONE]?.size)
    }



}
