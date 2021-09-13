package ar.edu.itba.pam.mygrocery.db.product;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "products", foreignKeys = @ForeignKey(entity = CategoryEntity.class, parentColumns = "category_id", childColumns = "my_category_id", onDelete = CASCADE))
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    public int product_id;

    @ColumnInfo(name = "my_category_id")
    public int categoryId;

    @ColumnInfo(name = "product_name")
    public String name;

    @ColumnInfo(name = "product_description")
    public String description;
}