package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.ProductViewHolder;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class MarketProductAdapter extends RecyclerView.Adapter<MarketProductViewHolder> {

    private final List<Product> dataset;

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

    @NonNull
    @Override
    public MarketProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_product_viewholder, parent, false);
        return new MarketProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketProductViewHolder holder, int position) {
        holder.bind(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }
}
