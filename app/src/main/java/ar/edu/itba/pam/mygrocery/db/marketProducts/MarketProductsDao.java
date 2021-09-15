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

    @Query("SELECT * FROM markets WHERE market_id = :marketId")
    Flowable<MarketAllProducts> getMarketAndProducts(final Long marketId);


    @Insert
    void insert(final MarketAllProductsEntity marketAllProductsEntity);

    @Delete
    void delete(final MarketAllProductsEntity marketAllProductsEntity);
}
