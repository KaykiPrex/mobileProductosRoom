package com.example.covidapp.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.covidapp.modelo.Producto;

import java.util.List;
@Dao
public interface ProductoDAO {

    @Query("SELECT COUNT(*) FROM "+Producto.TABLE_NAME)
    int count();

    @Query("SELECT* FROM "+Producto.TABLE_NAME)
    List<Producto> getAllProducts();

    @Query("SELECT* FROM "+Producto.TABLE_NAME+
            " WHERE producto.stock = 1")
    List<Producto> getProductsSTOCK();

    @Query("SELECT* FROM "+Producto.TABLE_NAME+
            " WHERE producto.stock = 0")
    List<Producto> getProductsNOSTOCK();

   /* @Query("SELECT* FROM "+Producto.TABLE_NAME+
            " WHERE producto.categoria =:category")
    List<Producto> getProductsCategory(String category);*/

    @Query("SELECT _id FROM "+Producto.TABLE_NAME+" ORDER BY "+Producto.COLUMN_ID+" DESC LIMIT 1")
    long getLastID();

    @Query("SELECT* FROM "+Producto.TABLE_NAME+" ORDER BY "+Producto.COLUMN_ID+" DESC LIMIT 1")
    Producto getLastProduct();

    @Insert
    long insert(Producto producto);

    @Update
    void update(Producto producto);

    @Delete
    void delete(Producto producto);
}
