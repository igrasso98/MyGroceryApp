package ar.edu.itba.pam.mygrocery.home.markets.repository;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.market.MarketDao;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProducts;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProductsEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketProduct;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketProductsDao;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import io.reactivex.Flowable;

public class RoomMarketsRepository implements MarketsRepository {

    private final MarketDao marketDao;
    private final MarketProductsDao marketProductsDao;
    private final MarketMapper marketMapper;
    private final ProductMapper productMapper;

    private Flowable<List<Market>> markets;

    public RoomMarketsRepository(final MarketDao marketDao, final MarketProductsDao marketProductsDao, final MarketMapper marketMapper, final ProductMapper productMapper) {
        this.marketDao = marketDao;
        this.marketProductsDao = marketProductsDao;
        this.marketMapper = marketMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Flowable<List<Market>> getMarkets() {
        if (markets == null) {
            Flowable<List<MarketEntity>> marketsList = marketDao.getMarkets();
            markets = marketsList.map(marketMapper::toMarketModel);
        }
        return markets;
    }

    @Override
    public Flowable<List<Product>> getMarketProductsList(final Long marketId) {
        Flowable<List<MarketProduct>> marketAllProducts = marketProductsDao.getMarketProducts(marketId);
        return marketAllProducts.map(marketAllProduct -> productMapper.toProductsByMarketProducts(marketId, marketAllProduct));
    }

    @Override
    public void createMarket(String market) {
        MarketEntity marketEntity = marketMapper.toEntityFromString(market);
        try {
            marketDao.insert(marketEntity);
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public Flowable<List<Market>> getProductsByMarket() {
        if (markets == null) {
            Flowable<List<MarketAllProducts>> marketsList = marketDao.getMarketAndProductsQty();
            markets = marketsList.map(marketMapper::toProductsByMarketsModel);
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
    public void buyProduct(Long productId, Long markerId) {
        MarketAllProductsEntity marketAllProductsEntity = new MarketAllProductsEntity();
        marketAllProductsEntity.productId = productId;
        marketAllProductsEntity.marketId = markerId;
        marketAllProductsEntity.isCheck = false;
        marketProductsDao.insert(marketAllProductsEntity);

    }

    @Override
    public void checkProduct(Long marketProductId, Boolean check) {
        marketProductsDao.updateIsCheck(marketProductId,check);
    }

    @Override
    public void closeMarketProductsList(Long marketId) {
        marketProductsDao.clearMarket(marketId);
    }
}
