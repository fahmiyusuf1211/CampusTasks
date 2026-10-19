# CampusTasks — Kotlin Domain Model

## 1. Deskripsi Proyek

CampusTasks merupakan proyek implementasi domain model menggunakan bahasa pemrograman Kotlin. Proyek ini berfokus pada pemodelan data tugas, validasi data, normalisasi input, perubahan status tugas, serta operasi pencarian dan pengelompokan tugas.

Proyek ini dibuat sebagai bagian dari tugas praktikum Kotlin.

## 2. Fitur Utama

- **Task Model:** merepresentasikan data tugas dengan properti yang terstruktur.
- **Task Priority:** mengatur tingkat prioritas tugas.
- **Task Status:** merepresentasikan status pengerjaan tugas.
- **Data Validation:** memeriksa validitas ID, judul, estimasi waktu, dan tag.
- **Data Normalization:** membersihkan spasi pada judul, deskripsi, dan tag.
- **Task Factory:** membuat tugas dan mengembalikan hasil berupa `Success` atau `Invalid`.
- **Task State Transition:** mengubah status tugas menggunakan `markInProgress()` dan `markDone()`.
- **Task Queries:** menyediakan operasi untuk mengambil tugas yang belum selesai, menyaring prioritas, mencari kata kunci, mengelompokkan status, dan mencari berdasarkan ID.

## 3. Struktur Domain Model

### Task

`Task` merupakan data class yang memiliki properti berikut:

| Properti | Tipe | Keterangan |
|---|---|---|
| `id` | `String` | Identitas tugas |
| `title` | `String` | Judul tugas |
| `description` | `String?` | Deskripsi opsional |
| `priority` | `TaskPriority` | Prioritas tugas |
| `status` | `TaskStatus` | Status pengerjaan |
| `estimatedMinutes` | `Int` | Estimasi waktu pengerjaan |
| `tags` | `Set<String>` | Kumpulan tag tugas |

### TaskPriority

Enum `TaskPriority` memiliki tiga nilai:

- `LOW`
- `MEDIUM`
- `HIGH`

Nilai default prioritas adalah `MEDIUM`.

### TaskStatus

Enum `TaskStatus` memiliki tiga nilai:

- `TODO`
- `IN_PROGRESS`
- `DONE`

Nilai default status adalah `TODO`.

### Identifiable

Interface `Identifiable` menyediakan properti `id` bertipe `String`. Interface ini digunakan agar pencarian berdasarkan ID dapat diterapkan secara generik.

## 4. Aturan Validasi

Validasi tugas menggunakan aturan berikut:

| Data | Aturan |
|---|---|
| ID | Panjang 3–80 karakter |
| Judul | Panjang 3–80 karakter setelah spasi awal dan akhir dihapus |
| Estimasi waktu | 5–480 menit |
| Tag | Tidak boleh berupa teks kosong atau hanya spasi |

`TaskValidator` mengembalikan nilai `true` jika seluruh aturan validasi terpenuhi dan `false` jika terdapat aturan yang tidak terpenuhi.

## 5. Normalisasi Data

Normalisasi dilakukan untuk menjaga konsistensi data:

- ID dan judul dibersihkan menggunakan `trim()` pada proses pembuatan tugas.
- Deskripsi yang kosong setelah normalisasi diubah menjadi `null`.
- Spasi awal dan akhir pada tag dihapus.
- Tag kosong dibuang dan tag duplikat dihilangkan karena menggunakan `Set`.

## 6. Pembuatan Tugas

Pembuatan tugas dilakukan melalui `TaskFactory.create()`.

Hasil pembuatan tugas direpresentasikan oleh sealed interface `CreateTaskResult`, yang memiliki dua kemungkinan:

- `CreateTaskResult.Success`: pembuatan tugas berhasil dan menyimpan objek `Task`.
- `CreateTaskResult.Invalid`: pembuatan tugas ditolak dan menyimpan pesan kesalahan.

## 7. Perubahan Status Tugas

`Task` menyediakan dua