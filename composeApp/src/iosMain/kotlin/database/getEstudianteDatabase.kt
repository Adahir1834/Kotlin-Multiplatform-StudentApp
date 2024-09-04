package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getEstudianteDatabase(): EstudianteDatabase {
    val dbFile = NSHomeDirectory() + "/estudiante.db"
    return Room.databaseBuilder<EstudianteDatabase>(
        name = dbFile,
        factory = { EstudianteDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}