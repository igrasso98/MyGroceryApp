package ar.edu.itba.pam.mygrocery.db.marketProducts;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MarketProductsDao {
    @Query("SELECT * FROM markets")
    Flowable<List<MarketAllProducts>> getMarketsAndProducts();

    @Insert
    void insert(final MarketAllProductsEntity categoryProductsEntity);

    @Insert
    void insertAll(final List<MarketAllProductsEntity> categoryProductsEntity);

    @Delete
    void delete(final MarketAllProductsEntity categoryProductsEntity);
}
