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

    @Query("SELECT * FROM markets")
    Flowable<List<MarketAllProducts>> getMarketsAndProducts();

    @Insert
    Long insert(final MarketEntity market);

    @Insert
    void insertAll(final List<MarketEntity> markets);

    @Delete
    void delete(final MarketEntity market);
}
