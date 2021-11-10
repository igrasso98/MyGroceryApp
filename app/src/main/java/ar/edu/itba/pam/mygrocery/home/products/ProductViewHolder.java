package ar.edu.itba.pam.mygrocery.home.products;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

// RESPONSABILIDAD DE REPRESENTAR LOS DATOS EN LA PANTALLA
public class ProductViewHolder extends RecyclerView.ViewHolder {

    private OnBuyProductClickedListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final Product model, final List<Market> markets) {
        final TextView productName = itemView.findViewById(R.id.product_name);
        productName.setText(model.getName());
        final TextView productDescription = itemView.findViewById(R.id.product_description);
        productDescription.setText(model.getDescription());
        final ImageView addProductButton = itemView.findViewById(R.id.shop_product);
        addProductButton.setOnClickListener(v -> {
            if (listener != null) {
                bindMarketDialog(itemView.getContext(), model.getId(), markets);
            }
        });
    }

    public void setOnClickListener(final OnBuyProductClickedListener listener) {
        this.listener = listener;
    }

    private void bindMarketDialog(final Context context, final Long productId, final List<Market> markets) {

        AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
        myDialog.setTitle("Seleccionar Mercado");

        final Spinner marketsSpinner = new Spinner(context);
        ArrayAdapter<Market> marketArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, markets);
        marketArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marketsSpinner.setAdapter(marketArrayAdapter);
        myDialog.setView(marketsSpinner);

        myDialog.setPositiveButton("Seleccionar", (dialogInterface, i) -> AsyncTask.execute(() -> listener.onBuyProductClicked(productId, ((Market) marketsSpinner.getSelectedItem()).getId())));
        myDialog.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());
        myDialog.show();
    }
}
