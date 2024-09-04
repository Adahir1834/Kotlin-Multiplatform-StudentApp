package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EstudianteDao {

    // Obtener todos los estudiantes
    @Query("SELECT * FROM estudiante")
    suspend fun obtenerTodos(): List<Estudiante>

    // Insertar uno o más estudiantes
    @Insert
    suspend fun insertar(estudiante: Estudiante)

    // Actualizar un estudiante
    @Update
    suspend fun actualizar(estudiante: Estudiante)

    // Eliminar un estudiante
    @Query("DELETE FROM estudiante WHERE id = :id")
    suspend fun eliminar(id: Int)
}
