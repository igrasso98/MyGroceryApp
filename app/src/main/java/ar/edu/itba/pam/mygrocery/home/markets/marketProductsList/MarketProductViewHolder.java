package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class MarketProductViewHolder extends RecyclerView.ViewHolder {
    public MarketProductViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final Product model) {
        final TextView productName = itemView.findViewById(R.id.product_name);
        productName.setText(model.getName());
        final TextView productDescription = itemView.findViewById(R.id.product_description);
        productDescription.setText(model.getDescription());
    }
}
