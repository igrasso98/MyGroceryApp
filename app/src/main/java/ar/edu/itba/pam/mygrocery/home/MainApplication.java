package ar.edu.itba.pam.mygrocery.home;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProductsEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainApplication extends Application {
    private static final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Completable.fromAction(() -> {
            createCategoryDataSet();
            createMarketsDataSet();
            autorestockProducts();
        }).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
    }

    private void createCategoryDataSet() {
        final List<String> categories = new ArrayList<String>() {{
            add("Carnes");
            add("Frutas y Verduras");
        }};
        for (int i = 0; i < 2; i++) {
            final CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.category_id = Long.valueOf(i);
            categoryEntity.name = categories.get(i);
            MyGroceryDb.getInstance(getApplicationContext()).categoryDao().insert(categoryEntity);
        }
    }

    private void createMarketsDataSet() {
        final List<String> markets = new ArrayList<String>() {{
            add("Mi Lista");
        }};
        for (int i = 0; i < 1; i++) {
            final MarketEntity marketEntity = new MarketEntity();
            marketEntity.market_id = Long.valueOf(i);
            marketEntity.name = markets.get(i);
            MyGroceryDb.getInstance(getApplicationContext()).marketDao().insert(marketEntity);
        }
    }

    private void autorestockProducts() {
        final MyGroceryDb db = MyGroceryDb.getInstance(getApplicationContext());
        final Flowable<List<ProductEntity>> entities = db.productDao().getProducts();
        final List<Product> products = entities.map(v -> new ProductMapper().toProducts(v)).blockingFirst();

        final Long currentTimestamp = System.currentTimeMillis();
        for (Product product : products) {
            double days = (currentTimestamp - product.getLastPurchased()) / ((double) DAY_IN_MILLIS);
            int addTimes = (int) days / product.getAutorestock();

            for (int i = 0; i < addTimes; i++) {
                final MarketAllProductsEntity entity = new MarketAllProductsEntity();
                entity.isCheck = false;
                entity.productId = product.getId();
                entity.marketId = product.getMarketId();
                db.marketProductsDao().insert(entity);
            }

            db.productDao().updateLastPurchased(product.getId(), product.getLastPurchased());
        }
    }

//    public byte[] ImageToByteArray() {
//        BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ImageIO.write(bImage, "jpg", bos);
//        byte[] data = bos.toByteArray();
//    }
}
