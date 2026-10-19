
package com.example.campustasks.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TaskValidatorTest {

    @Test
    fun taskDibuatDenganDataYangBenar() {
        val task = Task(
            id = "T001",
            title = "Mengerjakan tugas Kotlin",
            estimatedMinutes = 60
        )

        assertEquals("T001", task.id)
        assertEquals("Mengerjakan tugas Kotlin", task.title)
        assertEquals(60, task.estimatedMinutes)
        assertEquals(TaskPriority.MEDIUM, task.priority)
        assertEquals(TaskStatus.TODO, task.status)
    }

    @Test
    fun tugasValidMenghasilkanTrue() {
        val task = Task(
            id = "T001",
            title = "Mengerjakan tugas Kotlin",
            estimatedMinutes = 60
        )

        assertTrue(TaskValidator.validate(task))
    }

    @Test
    fun idKosongMenghasilkanFalse() {
        val task = Task(
            id = "",
            title = "Mengerjakan tugas Kotlin",
            estimatedMinutes = 60
        )

        assertFalse(TaskValidator.validate(task))
    }

    @Test
    fun judulKosongMenghasilkanFalse() {
        val task = Task(
            id = "T001",
            title = "   ",
            estimatedMinutes = 60
        )

        assertFalse(TaskValidator.validate(task))
    }

    @Test
    fun estimasiWaktuNolMenghasilkanFalse() {
        val task = Task(
            id = "T001",
            title = "Mengerjakan tugas Kotlin",
            estimatedMinutes = 0
        )

        assertFalse(TaskValidator.validate(task))
    }


    @Test
    fun markInProgressMengubahStatusTugas() {
        val task = Task(
            id = "T002",
            title = "Belajar Kotlin",
            estimatedMinutes = 45
        )

        val updatedTask = task.markInProgress()

        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.status)
        assertEquals(TaskStatus.TODO, task.status)
    }

    @Test
    fun markDoneMengubahStatusTugas() {
        val task = Task(
            id = "T003",
            title = "Menyelesaikan praktikum",
            estimatedMinutes = 60
        )

        val updatedTask = task.markDone()

        assertEquals(TaskStatus.DONE, updatedTask.status)
        assertEquals(TaskStatus.TODO, task.status)
    }


    @Test
    fun factoryMenghasilkanSuccessUntukDataValid() {
        val result = TaskFactory.create(
            id = "T004",
            title = "Belajar Kotlin",
            estimatedMinutes = 45
        )

        assertTrue(result is CreateTaskResult.Success)
    }

    @Test
    fun factoryMenghasilkanInvalidUntukIdKosong() {
        val result = TaskFactory.create(
            id = "",
            title = "Belajar Kotlin",
            estimatedMinutes = 45
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun idTerlaluPendekMenghasilkanFalse() {
        val task = Task(
            id = "AB",
            title = "Belajar Kotlin",
            estimatedMinutes = 30
        )

        assertFalse(TaskValidator.validate(task))
    }

    @Test
    fun estimasiWaktuMelebihiBatasMenghasilkanFalse() {
        val task = Task(
            id = "T001",
            title = "Belajar Kotlin",
            estimatedMinutes = 481
        )

        assertFalse(TaskValidator.validate(task))
    }

    @Test
    fun tagKosongMenghasilkanFalse() {
        val task = Task(
            id = "T001",
            title = "Belajar Kotlin",
            estimatedMinutes = 30,
            tags = setOf("Kotlin", "   ")
        )

        assertFalse(TaskValidator.validate(task))
    }


}
