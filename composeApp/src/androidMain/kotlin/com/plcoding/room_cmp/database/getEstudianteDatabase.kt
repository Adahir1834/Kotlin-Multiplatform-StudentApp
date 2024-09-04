package com.plcoding.room_cmp.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.EstudianteDatabase

fun getEstudianteDatabase(context: Context): EstudianteDatabase {
    val dbFile = context.getDatabasePath("estudiante.db")
    return Room.databaseBuilder<EstudianteDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}