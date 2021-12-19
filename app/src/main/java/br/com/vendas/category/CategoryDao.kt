package br.com.vendas.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import br.com.vendas.data.BaseDao

@Entity(tableName = "category")
data class Category(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

@Dao
abstract class CategoryDao : BaseDao<Category> {

    @Query("Select * from category")
    abstract fun getData() : LiveData<List<Category>>

}