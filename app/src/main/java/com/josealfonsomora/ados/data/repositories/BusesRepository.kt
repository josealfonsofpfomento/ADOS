package com.josealfonsomora.ados.data.repositories

import android.database.sqlite.SQLiteDatabase
import com.josealfonsomora.ados.data.ReadableSqlLite
import com.josealfonsomora.ados.data.room.AdosDatabaseRoom
import com.josealfonsomora.ados.data.room.AutobusEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BusesRepository @Inject constructor(
    private val roomDB: AdosDatabaseRoom,
    @ReadableSqlLite private val sqliteDB: SQLiteDatabase
) {

    fun getBuses(): Flow<List<AutobusEntity>> = roomDB.autobusDao().getAll()

    suspend fun deleteBus(bus: AutobusEntity){
        withContext(IO) {
            roomDB.autobusDao().deleteAutobus(bus)
        }
    }
}