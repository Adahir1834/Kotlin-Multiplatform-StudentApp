package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiante")
data class Estudiante(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val nombre: String,
    val edad: Int,
    val grupo: String,
    val promediogeneral: Double
)