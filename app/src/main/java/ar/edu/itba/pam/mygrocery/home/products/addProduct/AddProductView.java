package ar.edu.itba.pam.mygrocery.home.products.addProduct;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;

public interface AddProductView {
    void bind(List<Market> markets,List<Category> categories);
}
