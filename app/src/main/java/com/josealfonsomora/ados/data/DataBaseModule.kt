package com.josealfonsomora.ados.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.josealfonsomora.ados.data.room.AdosDatabaseRoom
import com.josealfonsomora.ados.data.sqlite.AdosDatabaseSqlite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ReadableSqlLite

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WritableSqlLite

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    @WritableSqlLite
    fun providesSqliteDb(@ApplicationContext context: Context): SQLiteDatabase =
        AdosDatabaseSqlite(context).writableDatabase

    @Singleton
    @Provides
    @ReadableSqlLite
    fun providesReadableSqliteDb(@ApplicationContext context: Context): SQLiteDatabase =
        AdosDatabaseSqlite(context).readableDatabase

    @Singleton
    @Provides
    fun providesSqliteHelper(@ApplicationContext context: Context): AdosDatabaseSqlite {
        return AdosDatabaseSqlite(context)
    }

    @Singleton
    @Provides
    fun providesRoomDb(@ApplicationContext context: Context): AdosDatabaseRoom {
        val roomDb = Room.databaseBuilder(
            context.applicationContext, AdosDatabaseRoom::class.java, "ados_room"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa do Franco, Santiago de Compostela', '2025-02-18', '9:00', '14:00')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa Real, A Coruña', '2025-02-18', '10:00', '15:00')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Avenida de Castrelos, Vigo', '2025-02-18', '8:30', '13:30')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa do Paseo, Ourense', '2025-02-18', '7:45', '12:45')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa de San Andrés, A Coruña', '2025-02-18', '9:15', '14:15')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa Urzaiz, Vigo', '2025-02-18', '10:30', '16:00')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa Nova, Lugo', '2025-02-18', '8:00', '13:00')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Praza Maior, Ourense', '2025-02-18', '9:45', '15:30')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Avenida de Lugo, Santiago de Compostela', '2025-02-18', '7:30', '12:30')")
                db.execSQL("INSERT INTO autobuses (direccion, fecha, inicio, fin) VALUES ('Rúa da Marina, Ferrol', '2025-02-18', '10:00', '14:45')")
            }
        }).build()
        return roomDb
    }
}