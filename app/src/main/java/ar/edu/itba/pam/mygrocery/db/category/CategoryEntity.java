package ar.edu.itba.pam.mygrocery.db.category;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class CategoryEntity {
    @PrimaryKey()
    @ColumnInfo(name = "category_id")
    public Long category_id;

    @ColumnInfo(name = "category_name")
    public String name;
}