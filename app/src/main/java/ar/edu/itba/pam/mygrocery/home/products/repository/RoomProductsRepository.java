package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.List;

import io.reactivex.Flowable;
import ar.edu.itba.pam.mygrocery.db.product.ProductDao;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class RoomProductsRepository implements ProductsRepository {

    private final ProductDao dao;
    private final ProductMapper mapper;

    private Flowable<List<Product>> products;

    public RoomProductsRepository(final ProductDao dao, final ProductMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        if (products == null) {
            Flowable<List<ProductEntity>> pas = dao.getProducts();
            products = pas.map(mapper::toModel);
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
