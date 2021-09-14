package ar.edu.itba.pam.mygrocery.db.market;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "markets")
public class MarketEntity {
    @PrimaryKey()
    @ColumnInfo(name = "market_id")
    public int market_id;

    @ColumnInfo(name = "market_name")
    public String name;

    @ColumnInfo(name = "category_image", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;


}