package ar.edu.itba.pam.mygrocery.home.ui;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public interface HomeView {

    void showProducts();

    void showProductDetails(String id);

    void showMarkets();

    void bindProducts(final List<Product> model);

}
