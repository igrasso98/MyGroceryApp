package ar.edu.itba.pam.mygrocery.home.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

//RESPONSABLE DE CONSTRUIR VIEWHOLDERS Y MODELARLOS
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private final List<Product> dataset;
    private OnProductClickedListener listener;

    public ProductAdapter() {
        dataset = new ArrayList<>();
    }

    public void setOnProductClickedListener(final OnProductClickedListener listener) {
        this.listener = listener;
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
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_viewholder, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(dataset.get(position));
        holder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }
}
