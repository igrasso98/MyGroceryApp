package ar.edu.itba.pam.mygrocery.db.product;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products", indices = {@Index(value = {"product_name", "product_description", "my_category_id"}, unique = true)})
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

    @ColumnInfo(name = "product_autorestock")
    public Integer autorestock;

    @ColumnInfo(name = "product_last_purchased")
    public Long lastPurchased;
}
