package com.davidsantiagoiriarte.data.database.daos

import androidx.room.*
import com.davidsantiagoiriarte.data.database.entities.DBProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM DBProduct")
    fun getAll(): Flow<List<DBProduct>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<DBProduct>)

    @Update
    suspend fun update(product: DBProduct)

    @Query("UPDATE DBProduct SET cant=0")
    suspend fun setProductsCantToZero()

}
