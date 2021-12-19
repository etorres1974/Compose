package br.com.vendas.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import br.com.vendas.data.BaseDao

@Entity(tableName = "product")
data class Product(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

@Dao
abstract class ProductDao : BaseDao<Product> {

    @Query("Select * from product")
    abstract fun getData() : LiveData<List<Product>>

}