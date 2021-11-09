package ar.edu.itba.pam.mygrocery.db.market;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProducts;
import io.reactivex.Flowable;

@Dao
public interface MarketDao {

    @Query("SELECT * FROM markets")
    Flowable<List<MarketEntity>> getMarkets();

    @Query("SELECT markets.*, COUNT(products.product_id) productsQty\n" +
            "FROM markets\n" +
            "INNER JOIN marketLists\n" +
            "ON markets.market_id = marketLists.my_market_id\n" +
            "INNER JOIN products\n" +
            "ON marketLists.my_product_id = products.product_id\n" +
            "GROUP BY market_id\n")
    Flowable<List<MarketAllProducts>> getMarketAndProductsQty();

    @Insert
    Long insert(final MarketEntity market);

    @Insert
    void insertAll(final List<MarketEntity> markets);

    @Delete
    void delete(final MarketEntity market);
}
