package ar.edu.itba.pam.mygrocery.db.category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM categories")
    Flowable<List<CategoryEntity>> getCategories();

    @Insert
    Long insert(final CategoryEntity category);

    @Insert
    void insertAll(final List<CategoryEntity> categories);

    @Delete
    void delete(final CategoryEntity category);
}
