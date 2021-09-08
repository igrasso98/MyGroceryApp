package ar.edu.itba.pam.mygrocery.db.product;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int uid;

    @ColumnInfo(name = "name")
    public String name;
}