package ar.edu.itba.pam.mygrocery.db.categoryProducts;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;
import io.reactivex.Flowable;

@Dao
public interface CategoryProductsDao {

    @Query("SELECT * FROM categories")
    Flowable<List<CategoryAllProducts>> getCategoriesAndProducts();
}
