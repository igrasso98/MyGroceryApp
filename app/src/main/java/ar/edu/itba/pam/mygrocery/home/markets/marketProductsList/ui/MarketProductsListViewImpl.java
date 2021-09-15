package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.MarketProductAdapter;

public class MarketProductsListViewImpl extends RecyclerView implements MarketProductsListView {
    public MarketProductsListViewImpl(@NonNull Context context) {
        super(context);
    }

    public MarketProductsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarketProductsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bind(MarketProductAdapter marketProductAdapter) {
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        setAdapter(marketProductAdapter);
    }
}
