package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public interface MarketProductsView {
    void bind(final List<Product> model);
    void setActivityTitle(String marketName);
}
