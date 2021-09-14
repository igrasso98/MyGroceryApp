package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryProductsDao;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryAllProducts;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import io.reactivex.Flowable;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class RoomProductsRepository implements ProductsRepository {

    private final CategoryProductsDao dao;
    private final ProductMapper mapper;

    private Flowable<List<Category>> categories;

    public RoomProductsRepository(final CategoryProductsDao dao, final ProductMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }
    @Override
    public Flowable<List<Category>> getProductsByCategory() {
        if(categories == null) {
            Flowable<List<CategoryAllProducts>> productsList = dao.getCategoriesAndProducts();
            categories = productsList.map(mapper::toProductsByCategoryModel);
        }
        return categories;
    }

    @Override
    public void addProducts(List<Product> products) {
//        this.categories = null;
//        dao.insertAll(mapper.toEntity(products));
    }

    @Override
    public void addProduct(Product product) {
//        this.categories = null;
//        dao.insert(mapper.toEntity(product));
    }

    @Override
    public void removeProduct(Product product) {
//        this.categories = null;
//        dao.delete(mapper.toEntity(product));
    }
}
