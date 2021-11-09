package ar.edu.itba.pam.mygrocery.home.markets.ui;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;

public interface MarketsView {

    void showMarketProducts(Long marketId, String name);

    void bindMarkets(List<Market> model);
}
