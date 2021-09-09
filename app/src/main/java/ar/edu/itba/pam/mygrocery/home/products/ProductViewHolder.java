package ar.edu.itba.pam.mygrocery.home.products;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

// RESPONSABILIDAD DE REPRESENTAR LOS DATOS EN LA PANTALLA
public class ProductViewHolder extends RecyclerView.ViewHolder {

    private OnProductClickedListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final Product model) {
        final TextView textView = itemView.findViewById(R.id.text);
        textView.setText(model.getProductName());
        itemView.setOnClickListener(v -> {
            if(listener != null) {
                listener.onClicked(model.getProductName());
            }
        });
    }

    public void setOnClickListener(final OnProductClickedListener listener) {
        this.listener = listener;
    }
}