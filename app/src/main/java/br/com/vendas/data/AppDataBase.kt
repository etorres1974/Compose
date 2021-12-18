package br.com.vendas.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Category::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao() : CategoryDao
}

fun provideRoom(application : Application) =
    Room.databaseBuilder(application,
    AppDataBase::class.java,
    "database-name"
    ).build()