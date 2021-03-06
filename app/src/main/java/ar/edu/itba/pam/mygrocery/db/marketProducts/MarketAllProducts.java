package ar.edu.itba.pam.mygrocery.db.marketProducts;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

public class MarketAllProducts {
    @Embedded
    public MarketEntity market;

    public int productsQty;
}
