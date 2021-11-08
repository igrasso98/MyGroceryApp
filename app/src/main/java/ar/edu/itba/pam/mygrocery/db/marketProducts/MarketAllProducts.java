//package ar.edu.itba.pam.mygrocery.db.marketProducts;
//
//import androidx.room.Embedded;
//import androidx.room.Junction;
//import androidx.room.Relation;
//
//import java.util.List;
//
//import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
//import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
//
//public class MarketAllProducts {
//    @Embedded
//    public MarketEntity market;
//
//    @Relation(entityColumn = "product_id", parentColumn = "market_id", associateBy = @Junction(
//            value = MarketAllProductsEntity.class,
//            parentColumn = "my_market_id",
//            entityColumn = "my_product_id"
//    ))
//    public List<ProductEntity> products;
//}
