package ar.edu.itba.pam.mygrocery.home.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    private final List<Category> dataset;
    private OnBuyProductClickedListener listener;

    public ProductsAdapter() {
        dataset = new ArrayList<>();
    }

    public void setDataset(final List<Category> newDataset) {
        dataset.clear();
        if (newDataset != null) {
            dataset.addAll(newDataset);
        }
        notifyDataSetChanged();
    }

    public void setOnBuyProductClickedListener(OnBuyProductClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_by_category, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setOnProductClickedListener(listener);
        productAdapter.setDataset(dataset.get(position).getProducts());
        holder.productsByCategory.setAdapter(productAdapter);
        holder.bind(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
