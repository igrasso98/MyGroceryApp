package ar.edu.itba.pam.mygrocery.db.marketProducts;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MarketProductsDao {
    @Query("SELECT products.*, marketLists.is_check, marketLists.market_product_id\n" +
            "FROM products\n" +
            "INNER JOIN marketLists\n" +
            "ON products.product_id = marketLists.my_product_id\n" +
            "AND marketLists.my_market_id = :marketId\n")
    Flowable<List<MarketProduct>> getMarketProducts(final Long marketId);

    @Insert
    void insert(final MarketAllProductsEntity marketAllProductsEntity);

    @Delete
    void delete(final MarketAllProductsEntity marketAllProductsEntity);

    @Query("UPDATE marketLists SET is_check = :check  WHERE market_product_id = :id")
    void updateIsCheck(Long id, Boolean check);

}
