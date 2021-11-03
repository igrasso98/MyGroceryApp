package ar.edu.itba.pam.mygrocery.db.market;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "markets")
public class MarketEntity {
    @PrimaryKey()
    @ColumnInfo(name = "market_id")
    public Long market_id;

    @ColumnInfo(name = "market_name")
    public String name;
}