package ar.edu.itba.pam.mygrocery.home.markets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.OnProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.ProductAdapter;
import ar.edu.itba.pam.mygrocery.home.products.ProductViewHolder;
import ar.edu.itba.pam.mygrocery.home.products.ProductsViewHolder;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class MarketAdapter extends RecyclerView.Adapter<MarketViewHolder> {

    private final List<Market> dataset;
    private OnMarketClickedListener listener;

    public MarketAdapter() {
        dataset = new ArrayList<>();
    }

    public void setOnMarketClickedListener(final OnMarketClickedListener listener) {
        this.listener = listener;
    }

    public void setDataset(final List<Market> newDataset) {
        dataset.clear();
        if (newDataset != null) {
            dataset.addAll(newDataset);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_viewholder, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        holder.bind(dataset.get(position));
        holder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }
}
