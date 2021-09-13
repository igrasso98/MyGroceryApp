package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.db.product.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductWithCategory;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class ProductMapper {
//    public List<Product> toModel(final List<ProductEntity> productEntities) {
//        final List<Product> list = new ArrayList<>();
//        for (final ProductEntity productEntity : productEntities) {
//            list.add(new Product(productEntity.name));
//        }
//        return list;
//    }

    public Map<Category, List<Product>> toProductsByCategoryModel(final List<ProductWithCategory> productsWithCategoryEntities) {
        final Map<Category, List<Product>> productsByCategory = new LinkedHashMap<>();
        for (final ProductWithCategory productWithCategory : productsWithCategoryEntities) {
            Category category = categoryFromEntity(productWithCategory.category);
            if (!productsByCategory.containsKey(category)) {
                productsByCategory.put(category, new ArrayList<>());
            }
            List<Product> currentProducts = productsByCategory.get(category);
            if (currentProducts != null) {
                Product product = productFromEntity(productWithCategory.product, category);
                currentProducts.add(product);
            }
            productsByCategory.put(category, currentProducts);
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

    private Category categoryFromEntity(CategoryEntity entity) {
        return new Category(entity.name, entity.image);
    }

    private Product productFromEntity(ProductEntity entity, Category category) {
        return new Product(entity.name, entity.description, category);
    }
}
