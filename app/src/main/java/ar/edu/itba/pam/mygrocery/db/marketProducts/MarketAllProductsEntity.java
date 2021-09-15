package ar.edu.itba.pam.mygrocery.db.marketProducts;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

@Entity(tableName = "marketLists",
        foreignKeys = {
        @ForeignKey(entity = MarketEntity.class, parentColumns = "market_id", childColumns = "my_market_id", onDelete = RESTRICT, onUpdate = CASCADE),
        @ForeignKey(entity = ProductEntity.class, parentColumns = "product_id", childColumns = "my_product_id", onDelete = RESTRICT, onUpdate = CASCADE)
})
public class MarketAllProductsEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "market_product_id")
    public Long marketProductId;

    @ColumnInfo(name = "my_market_id")
    @NonNull
    public Long marketId;

    @ColumnInfo(name = "my_product_id")
    @NonNull
    public Long productId;

    @ColumnInfo(name = "is_check")
    public boolean isCheck;
}