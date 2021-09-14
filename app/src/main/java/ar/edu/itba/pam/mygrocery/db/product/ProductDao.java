package ar.edu.itba.pam.mygrocery.db.product;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    Flowable<List<ProductEntity>> getProducts();

    @Insert
    void insert(final ProductEntity product);

    @Insert
    void insertAll(final List<ProductEntity> products);

    @Delete
    void delete(final ProductEntity product);
}
