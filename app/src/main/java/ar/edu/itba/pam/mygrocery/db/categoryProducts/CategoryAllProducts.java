package ar.edu.itba.pam.mygrocery.db.categoryProducts;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

public class CategoryAllProducts {
    @Embedded
    public CategoryEntity category;

    @Relation(parentColumn = "category_id", entityColumn = "my_category_id", entity = ProductEntity.class)
    public List<ProductEntity> products;
}
