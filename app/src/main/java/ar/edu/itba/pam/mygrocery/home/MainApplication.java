package ar.edu.itba.pam.mygrocery.home;

import android.app.Application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.db.product.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.product.ProductDb;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Completable.fromAction(() -> {
            createCategoryDataSet();
            ProductDb.getInstance(getApplicationContext()).productDao().insertAll(createDataSet());
        }).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
    }

    private void createCategoryDataSet() {
        for (int i = 0; i < 3; i++) {
            final CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.category_id = i;
            categoryEntity.name = "CATEGORY " + i;
            ProductDb.getInstance(getApplicationContext()).productDao().insertCategory(categoryEntity);
        }
    }

    private List<ProductEntity> createDataSet() {
        final List<ProductEntity> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final ProductEntity productEntity = new ProductEntity();
            productEntity.name = String.valueOf(Math.random());
            productEntity.categoryId = i % 3;
            list.add(productEntity);
        }

        return list;
    }

//    public byte[] ImageToByteArray() {
//        BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ImageIO.write(bImage, "jpg", bos);
//        byte[] data = bos.toByteArray();
//    }
}
