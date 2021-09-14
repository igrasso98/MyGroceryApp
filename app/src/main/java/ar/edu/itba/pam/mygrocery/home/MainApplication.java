package ar.edu.itba.pam.mygrocery.home;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProductsEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Completable.fromAction(() -> {
//            createCategoryDataSet();
//            createMarketsDataSet();
//            MyGroceryDb.getInstance(getApplicationContext()).productDao().insertAll(createProductDataSet());
        }).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
    }

    private void createCategoryDataSet() {
        for (int i = 0; i < 3; i++) {
            final CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.category_id = Long.valueOf(i);
            categoryEntity.name = "CATEGORY " + i;
            MyGroceryDb.getInstance(getApplicationContext()).categoryDao().insert(categoryEntity);
        }
    }

    private List<ProductEntity> createProductDataSet() {
        final List<ProductEntity> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final ProductEntity productEntity = new ProductEntity();
            productEntity.name = String.valueOf(Math.random());
            productEntity.categoryId = Long.valueOf(i % 3);
            list.add(productEntity);
        }
        return list;
    }

    private void createMarketsDataSet() {
        for (int i = 0; i < 3; i++) {
            final MarketEntity marketEntity = new MarketEntity();
            marketEntity.market_id = Long.valueOf(i);
            marketEntity.name = "MARKET " + i;
            MyGroceryDb.getInstance(getApplicationContext()).marketDao().insert(marketEntity);
        }

    }

//    public byte[] ImageToByteArray() {
//        BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ImageIO.write(bImage, "jpg", bos);
//        byte[] data = bos.toByteArray();
//    }
}
