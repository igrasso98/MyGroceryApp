package ar.edu.itba.pam.mygrocery.db.product;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductEntity.class}, version = 1)
abstract public class ProductDb extends RoomDatabase {
    private static final String NAME = "pm_db";
    private static ProductDb instance;

    public abstract ProductDao productDao();

    public static synchronized ProductDb getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductDb.class, NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
