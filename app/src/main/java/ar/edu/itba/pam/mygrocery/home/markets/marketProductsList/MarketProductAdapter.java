package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.OnBuyProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class MarketProductAdapter extends RecyclerView.Adapter<MarketProductViewHolder> {

    private final List<Product> dataset;
    private OnCheckProductClickedListener listener;

    public MarketProductAdapter() {
        dataset = new ArrayList<>();
    }

    public void setDataset(final List<Product> newDataset) {
        dataset.clear();
        if (newDataset != null) {
            dataset.addAll(newDataset);
        }
        notifyDataSetChanged();
    }

    public void setOnProductClickedListener(final OnCheckProductClickedListener listener) {
        this.listener = listener;
    }

    public boolean hasUncheckedProducts() {
        for (Product product : dataset) {
            if (!product.getChecked()) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public MarketProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_product_viewholder, parent, false);
        return new MarketProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketProductViewHolder holder, int position) {
        holder.bind(dataset.get(position));
        holder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }
}
