package ar.edu.itba.pam.mygrocery.db.product;

import androidx.room.Embedded;

public class ProductWithCategory {
    @Embedded
    public CategoryEntity category;

    @Embedded
    public ProductEntity product;
}
