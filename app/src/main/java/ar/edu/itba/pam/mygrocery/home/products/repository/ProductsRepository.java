package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import io.reactivex.Flowable;

public interface ProductsRepository {

    Flowable<List<Category>> getProductsByCategory();

    public void addProducts(List<Product> products);

    void addProduct(final Product product);

    void removeProduct(final Product product);

}
