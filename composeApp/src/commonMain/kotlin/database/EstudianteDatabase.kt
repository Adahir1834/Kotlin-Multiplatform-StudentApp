package database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Estudiante::class],
    version = 1
)
abstract class EstudianteDatabase: RoomDatabase() {

    abstract fun estudianteDao(): EstudianteDao

}