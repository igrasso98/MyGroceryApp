package ar.edu.itba.pam.mygrocery.home.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    private final Map<Category, List<Product>> dataset;

    public ProductsAdapter() {
        dataset = new LinkedHashMap<>();
    }

    public void setDataset(final Map<Category, List<Product>> newDataset) {
        dataset.clear();
        if (newDataset != null) {
            dataset.putAll(newDataset);
        }
        notifyDataSetChanged();
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
        productAdapter.setDataset((List<Product>) dataset.values().toArray()[position]);
        holder.productsByCategory.setAdapter(productAdapter);
        Category category = (Category) dataset.keySet().toArray()[position];
        holder.bind(category, dataset.get(category).size());
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }
}
