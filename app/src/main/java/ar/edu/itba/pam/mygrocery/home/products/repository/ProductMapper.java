package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryAllProducts;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

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
        productEntity.name = product.getName();
        return productEntity;
    }

    private Category categoryFromEntity(CategoryEntity entity, List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        if (productEntities != null) {
            for (ProductEntity productEntity : productEntities) {
                products.add(new Product(productEntity.name, productEntity.description));
            }

        }
        return new Category(entity.name, entity.image, products);
    }
}
