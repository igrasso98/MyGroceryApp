package ar.edu.itba.pam.mygrocery.home.markets.repository;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.market.MarketDao;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProducts;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketProductsDao;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import io.reactivex.Flowable;

public class RoomMarketsRepository implements MarketsRepository {

    private final MarketDao marketDao;
    private final MarketProductsDao marketProductsDao;
    private final MarketMapper mapper;

    private Flowable<List<Market>> markets;

    public RoomMarketsRepository(final MarketDao marketDao, final MarketProductsDao marketProductsDao, final MarketMapper mapper) {
        this.marketDao = marketDao;
        this.marketProductsDao = marketProductsDao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<Market>> getMarkets() {
        if (markets == null) {
            Flowable<List<MarketEntity>> marketsList = marketDao.getMarkets();
            markets = marketsList.map(mapper::toMarketModel);
        }
        return markets;
    }

    @Override
    public Flowable<Market> getMarketProductsList(final Long marketId) {

        Flowable<MarketAllProducts> marketAllProducts = marketProductsDao.getMarketAndProducts(marketId);
        Flowable<Market> market = marketAllProducts.map(mapper::toProductsByMarketModel);
        return market;
    }

    @Override
    public Flowable<List<Market>> getProductsByMarket() {
        if (markets == null) {
            Flowable<List<MarketAllProducts>> marketsList = marketDao.getMarketsAndProducts();
            markets = marketsList.map(mapper::toProductsByMarketsModel);
        }
        return markets;
    }

    @Override
    public void addProduct(Market market, Product product) {

    }

    @Override
    public void removeProduct(Market market, Product product) {

    }

    @Override
    public void buyProduct(Market market, Product product) {

    }
}
