package br.com.vendas.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.vendas.category.Category
import br.com.vendas.category.CategoryDao
import br.com.vendas.product.Product
import br.com.vendas.product.ProductDao

@Database(
    entities = [
        Category::class,
        Product::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}

fun provideRoom(application: Application) =
    Room.databaseBuilder(
        application,
        AppDataBase::class.java,
        "database-name"
    ).build()