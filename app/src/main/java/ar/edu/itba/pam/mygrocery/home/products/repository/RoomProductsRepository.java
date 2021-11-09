package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryDao;
import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryProductsDao;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryAllProducts;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductDao;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import io.reactivex.Flowable;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class RoomProductsRepository implements ProductsRepository {

    private final CategoryProductsDao categoryProductsDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final ProductMapper mapper;

    private Flowable<List<Category>> categories;

    public RoomProductsRepository(final CategoryProductsDao categoryProductsDao, final CategoryDao categoryDao, final ProductDao productDao, final ProductMapper mapper) {
        this.categoryProductsDao = categoryProductsDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<Category>> getProductsByCategory() {
        if (categories == null) {
            Flowable<List<CategoryAllProducts>> productsList = categoryProductsDao.getCategoriesAndProducts();
            categories = productsList.map(mapper::toProductsByCategoryModel);
        }
        return categories;
    }

    public Flowable<List<Category>> getCategories() {
        if (categories == null) {
            Flowable<List<CategoryEntity>> categoriesList = categoryDao.getCategories();
            categories = categoriesList.map(mapper::toCategoryModel);
        }
        return categories;
    }

    @Override
    public void createCategory(String category) {
        CategoryEntity categoryEntity = mapper.toEntityFromString(category);
        try {
            categoryDao.insert(categoryEntity);
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void addProducts(List<Product> products) {
//        this.categories = null;
//        dao.insertAll(mapper.toEntity(products));
    }

    @Override
    public void addProduct(Product product) {
        this.categories = null;
        try {
            productDao.insert(mapper.toEntity(product));
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void removeProduct(Product product) {
//        this.categories = null;
//        dao.delete(mapper.toEntity(product));
    }
}
