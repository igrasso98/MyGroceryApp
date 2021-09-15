package ar.edu.itba.pam.mygrocery.home.products;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

// RESPONSABILIDAD DE REPRESENTAR LOS DATOS EN LA PANTALLA
public class ProductViewHolder extends RecyclerView.ViewHolder {

    private OnBuyProductClickedListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final Product model) {
        final TextView productName = itemView.findViewById(R.id.product_name);
        productName.setText(model.getName());
        final TextView productDescription = itemView.findViewById(R.id.product_description);
        productDescription.setText(model.getDescription());
        final ImageView addProductButton = itemView.findViewById(R.id.shop_product);
        addProductButton.setOnClickListener(v -> {
            if (listener != null) {
                AsyncTask.execute(() -> listener.onBuyProductClicked(model.getId(), model.getMarketId()));
                Toast.makeText(itemView.getContext(), "Producto agregado exitosamente" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setOnClickListener(final OnBuyProductClickedListener listener) {
        this.listener = listener;
    }
}
