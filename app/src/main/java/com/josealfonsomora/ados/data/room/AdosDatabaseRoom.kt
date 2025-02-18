package com.josealfonsomora.ados.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AutobusEntity::class], version = 1, exportSchema = false)
abstract class AdosDatabaseRoom : RoomDatabase() {
    abstract fun autobusDao(): AutobusDao
}