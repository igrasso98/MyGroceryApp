package ar.edu.itba.pam.mygrocery.db.marketProducts;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

public class MarketProduct {
    @Embedded
    public ProductEntity product;

    @ColumnInfo(name = "is_check")
    public Boolean isCheck;

    @ColumnInfo(name = "market_product_id")
    public Long marketProductId;
}
