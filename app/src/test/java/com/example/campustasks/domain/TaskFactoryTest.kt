
package com.example.campustasks.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskFactoryTest {

    @Test
    fun membuatTaskDenganDataValidMenghasilkanSuccess() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)
    }

    @Test
    fun judulDinormalisasiDenganTrim() {
        val result = TaskFactory.create(
            id = "T001",
            title = "  Tugas Kotlin  ",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)
        if (result is CreateTaskResult.Success) {
            assertEquals("Tugas Kotlin", result.task.title)
        }
    }

    @Test
    fun deskripsiKosongMenjadiNull() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            description = "   ",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)
        if (result is CreateTaskResult.Success) {
            assertEquals(null, result.task.description)
        }
    }

    @Test
    fun tagDinormalisasiDanDuplikatDihapus() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            estimatedMinutes = 30,
            tags = setOf(" Kotlin ", "AI", "Kotlin", " ")
        )

        assertTrue(result is CreateTaskResult.Success)
        if (result is CreateTaskResult.Success) {
            assertEquals(setOf("Kotlin", "AI"), result.task.tags)
        }
    }

    @Test
    fun judulTerlaluPendekMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "T001",
            title = "AB",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun judulKosongMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "T001",
            title = "   ",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun judulTepatTigaKarakterMenghasilkanSuccess() {
        val result = TaskFactory.create(
            id = "T001",
            title = "ABC",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)
    }

    @Test
    fun judulTerlaluPanjangMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "T001",
            title = "A".repeat(81),
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun estimasiTepatLimaMenitMenghasilkanSuccess() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            estimatedMinutes = 5
        )

        assertTrue(result is CreateTaskResult.Success)
    }

    @Test
    fun estimasiKurangDariBatasMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            estimatedMinutes = 4
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun estimasiMelebihiBatasMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            estimatedMinutes = 481
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun idKosongMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "   ",
            title = "Tugas Kotlin",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun idTerlaluPendekMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "AB",
            title = "Tugas Kotlin",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun idTerlaluPanjangMenghasilkanInvalid() {
        val result = TaskFactory.create(
            id = "A".repeat(81),
            title = "Tugas Kotlin",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun dataValidMempertahankanPriority() {
        val result = TaskFactory.create(
            id = "T001",
            title = "Tugas Kotlin",
            priority = TaskPriority.HIGH,
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)
        if (result is CreateTaskResult.Success) {
            assertEquals(TaskPriority.HIGH, result.task.priority)
            assertEquals(TaskStatus.TODO, result.task.status)
        }
    }
}
