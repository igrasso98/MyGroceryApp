package ar.edu.itba.pam.mygrocery.home;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

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
            ProductDb.getInstance(getApplicationContext()).productDao().insertAll(createDataSet());
        }).onErrorComplete().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
    }

    private List<ProductEntity> createDataSet() {
        final List<ProductEntity> list = new ArrayList<>();

        for(int i =0; i< 10 ; i++) {
            final ProductEntity productEntity = new ProductEntity();
            productEntity.name = String.valueOf(Math.random());
            list.add(productEntity);
        }

        return list;
    }
}
