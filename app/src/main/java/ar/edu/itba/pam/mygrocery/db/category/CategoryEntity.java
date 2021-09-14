package ar.edu.itba.pam.mygrocery.db.category;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

import ar.edu.itba.pam.mygrocery.db.product.ProductEntity;

@Entity(tableName = "categories")
public class CategoryEntity {
    @PrimaryKey()
    @ColumnInfo(name = "category_id")
    public Long category_id;

    @ColumnInfo(name = "category_name")
    public String name;

    @ColumnInfo(name = "category_image", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

}