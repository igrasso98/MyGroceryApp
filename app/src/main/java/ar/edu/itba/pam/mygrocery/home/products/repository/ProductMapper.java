package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryAllProducts;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketProduct;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import io.reactivex.Flowable;

public class ProductMapper {

    public List<Category> toProductsByCategoryModel(final List<CategoryAllProducts> categoriesAllProducts) {
        final List<Category> productsByCategory = new ArrayList<>();
        for (final CategoryAllProducts categoryAllProducts : categoriesAllProducts) {
            Category category = categoryFromEntity(categoryAllProducts.category, categoryAllProducts.products);
            if (!productsByCategory.contains(category)) {
                productsByCategory.add(category);
            }
        }
        return productsByCategory;
    }

    public List<Category> toCategoryModel(final List<CategoryEntity> categoryEntities) {
        final List<Category> categories = new ArrayList<>();
        for (final CategoryEntity categoryEntity : categoryEntities) {
            Category category = categoryFromEntity(categoryEntity, null);
            if (!categories.contains(category)) {
                categories.add(category);
            }
        }
        return categories;
    }

    public List<ProductEntity> toEntity(final List<Product> products) {
        final List<ProductEntity> list = new ArrayList<>();
        for (final Product product : products) {
            final ProductEntity productEntity = toEntity(product);
            list.add(productEntity);
        }
        return list;
    }

    public ProductEntity toEntity(Product product) {
        return mapToEntity(product);
    }

    private ProductEntity mapToEntity(Product product) {
        final ProductEntity productEntity = new ProductEntity();
        productEntity.name = product.getName().trim();
        productEntity.description = product.getDescription().trim();
        productEntity.categoryId = product.getCategoryId();
        productEntity.marketId = product.getMarketId();
        productEntity.autorestock = product.getAutorestock();
        productEntity.lastPurchased = product.getLastPurchased();
        return productEntity;
    }

    public CategoryEntity toEntityFromString(String name) {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.name = name.trim();
        return categoryEntity;
    }

    private Category categoryFromEntity(CategoryEntity categoryEntity, List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        if (productEntities != null) {
            for (ProductEntity productEntity : productEntities) {
                products.add(new Product(productEntity.productId, productEntity.name, productEntity.description, categoryEntity.category_id, productEntity.marketId, productEntity.autorestock, productEntity.lastPurchased));
            }

        }
        return new Category(categoryEntity.category_id, categoryEntity.name, products);
    }

    public List<Product> toProductsByMarketProducts(Long marketId, List<MarketProduct> marketProducts) {
        List<Product> products = new ArrayList<>();
        for (MarketProduct marketProduct : marketProducts) {
            ProductEntity productEntity = marketProduct.product;
            products.add(new Product(productEntity.productId, productEntity.name, productEntity.description, productEntity.categoryId, marketId, marketProduct.isCheck, marketProduct.marketProductId, productEntity.autorestock, productEntity.lastPurchased));
        }

        return products;
    }

    public List<Product> toProducts(List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            products.add(new Product(productEntity.productId, productEntity.name, productEntity.description, productEntity.categoryId, productEntity.marketId, productEntity.autorestock, productEntity.lastPurchased));
        }

        return products;
    }
}
