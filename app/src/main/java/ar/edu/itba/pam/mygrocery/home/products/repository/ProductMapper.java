package ar.edu.itba.pam.mygrocery.home.products.repository;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class ProductMapper {
    public List<Product> toModel(final List<ProductEntity> productEntities) {
        final List<Product> list = new ArrayList<>();
        for (final ProductEntity productEntity : productEntities) {
            list.add(new Product(productEntity.name));
        }
        return list;
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
        productEntity.name = product.getProductName();
        return productEntity;
    }
}
