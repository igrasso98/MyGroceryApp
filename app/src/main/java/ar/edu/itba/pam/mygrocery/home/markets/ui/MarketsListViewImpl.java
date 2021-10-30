package ar.edu.itba.pam.mygrocery.home.markets.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.home.markets.MarketAdapter;

public class MarketsListViewImpl extends RecyclerView implements MarketsListView {
    public MarketsListViewImpl(@NonNull Context context) {
        super(context);
    }

    public MarketsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarketsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bind(MarketAdapter marketAdapter) {
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        setAdapter(marketAdapter);
    }
}
