package ar.edu.itba.pam.mygrocery.home.markets.repository;

import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import io.reactivex.Flowable;

public interface MarketsRepository {

    Flowable<List<Market>> getProductsByMarket();

    Flowable<List<Market>> getMarkets();

    Flowable<List<Product>> getMarketProductsList(final Long marketId);

    void createMarket(final String market);

    void addProduct(final Market market, final Product product);

    void removeProduct(final Market market, final Product product);

    void buyProduct(final Long productId, final Long marketId);

    public void checkProduct(Long marketProductId, Boolean check);

    void closeMarketProductsList(Long marketId);
}
