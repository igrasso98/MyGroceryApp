package ar.edu.itba.pam.mygrocery.db.product;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;

//@Entity(tableName = "products", foreignKeys = @ForeignKey(entity = CategoryEntity.class, parentColumns = "category_id", childColumns = "my_category_id", onDelete = CASCADE))
@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    public Long productId;

    @ColumnInfo(name = "my_category_id")
    public Long categoryId;

    @ColumnInfo(name = "my_market_id")
    public Long marketId;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_description")
    public String description;
}