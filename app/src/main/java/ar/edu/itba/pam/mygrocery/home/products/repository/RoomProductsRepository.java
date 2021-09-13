package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.db.product.ProductWithCategory;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import io.reactivex.Flowable;
import ar.edu.itba.pam.mygrocery.db.product.ProductDao;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class RoomProductsRepository implements ProductsRepository {

    private final ProductDao dao;
    private final ProductMapper mapper;

    private Flowable<Map<Category, List<Product>>> products;

    public RoomProductsRepository(final ProductDao dao, final ProductMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return null;
//        if (products == null) {
//            Flowable<List<ProductEntity>> pas = dao.getProducts();
//            products = pas.map(mapper::toModel);
//        }
//        return products;
    }

    @Override
    public Flowable<Map<Category, List<Product>>> getProductsByCategory() {
        if(products == null) {
            Flowable<List<ProductWithCategory>> productsList = dao.getProductsByCategory();
            if (productsList != null) {
                System.out.println("ENTRE");
            }
            products = productsList.map(mapper::toProductsByCategoryModel);
        }
        return products;
    }

    @Override
    public void addProducts(List<Product> products) {
        this.products = null;
        dao.insertAll(mapper.toEntity(products));
    }

    @Override
    public void addProduct(Product product) {
        this.products = null;
        dao.insert(mapper.toEntity(product));
    }

    @Override
    public void removeProduct(Product product) {
        this.products = null;
        dao.delete(mapper.toEntity(product));
    }
}
