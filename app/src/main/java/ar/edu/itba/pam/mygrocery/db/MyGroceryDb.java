package ar.edu.itba.pam.mygrocery.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ar.edu.itba.pam.mygrocery.db.category.CategoryDao;
import ar.edu.itba.pam.mygrocery.db.category.CategoryEntity;
import ar.edu.itba.pam.mygrocery.db.categoryProducts.CategoryProductsDao;
import ar.edu.itba.pam.mygrocery.db.market.MarketDao;
import ar.edu.itba.pam.mygrocery.db.market.MarketEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketAllProductsEntity;
import ar.edu.itba.pam.mygrocery.db.marketProducts.MarketProductsDao;
import ar.edu.itba.pam.mygrocery.db.product.ProductDao;
import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

@Database(entities = {ProductEntity.class, CategoryEntity.class, MarketEntity.class, MarketAllProductsEntity.class}, version = 25)
abstract public class MyGroceryDb extends RoomDatabase {
    private static final String NAME = "pm_db";
    private static MyGroceryDb instance;

    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
    public abstract MarketDao marketDao();
    public abstract CategoryProductsDao categoryProductsDao();
    public abstract MarketProductsDao marketProductsDao();

    public static synchronized MyGroceryDb getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyGroceryDb.class, NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
