package ar.edu.itba.pam.mygrocery.home.markets.repository;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProducts;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class MarketMapper {
    public List<Market> toProductsByMarketsModel(final List<MarketAllProducts> marketsAllProducts) {
        final List<Market> productsByMarket = new ArrayList<>();
        for (final MarketAllProducts marketAllProducts : marketsAllProducts) {
            Market market = toProductsByMarketModel(marketAllProducts);
            if (!productsByMarket.contains(market)) {
                productsByMarket.add(market);
            }
        }
        return productsByMarket;
    }

    public Market toProductsByMarketModel(final MarketAllProducts marketAllProducts) {
        Market market = marketFromEntity(marketAllProducts.market, marketAllProducts.products);
        return market;
    }


    public List<Market> toMarketModel(final List<MarketEntity> marketEntities) {
        final List<Market> markets = new ArrayList<>();
        for (final MarketEntity marketEntity : marketEntities) {
            Market market = marketFromEntity(marketEntity, null);
            if (!markets.contains(market)) {
                markets.add(market);
            }
        }
        return markets;
    }

    public List<MarketEntity> toEntity(final List<Market> markets) {
        final List<MarketEntity> list = new ArrayList<>();
        for (final Market market : markets) {
            final MarketEntity marketEntity = toEntity(market);
            list.add(marketEntity);
        }
        return list;
    }

    public MarketEntity toEntity(Market market) {
        return mapToEntity(market.getName());
    }

    public MarketEntity toEntityFromString(String market) {
        return mapToEntity(market);
    }

    private MarketEntity mapToEntity(String market) {
        final MarketEntity marketEntity = new MarketEntity();
        marketEntity.name = market;
        return marketEntity;
    }

    private Market marketFromEntity(MarketEntity marketEntity, List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        if (productEntities != null) {
            for (ProductEntity productEntity : productEntities) {
                products.add(new Product(productEntity.productId, productEntity.name, productEntity.description, productEntity.categoryId, productEntity.marketId));
            }
        }
        return new Market(marketEntity.market_id, marketEntity.name, products);
    }

    private Product productFromEntity(ProductEntity productEntity) {
        return new Product(productEntity.productId, productEntity.name, productEntity.description, productEntity.categoryId, productEntity.marketId);
    }
}
