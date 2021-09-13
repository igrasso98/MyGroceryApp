package ar.edu.itba.pam.mygrocery.db.product;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class CategoryEntity {
    @PrimaryKey()
    @ColumnInfo(name = "category_id")
    public int category_id;

    @ColumnInfo(name = "category_name")
    public String name;

    @ColumnInfo(name = "category_image", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;
}