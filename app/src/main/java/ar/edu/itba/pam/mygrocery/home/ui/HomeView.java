package ar.edu.itba.pam.mygrocery.home.ui;

import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public interface HomeView {

    void showProducts();

    void showMarketProducts(Long marketId);

    void showMarkets();

    void bindProducts(final List<Category> model);

    void bindMarkets(List<Market> model);

}
