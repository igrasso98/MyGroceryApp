package ar.edu.itba.pam.mygrocery.home.products;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.TypedValue;
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
                bindMarketDialog(itemView.getContext(), model.getId(), model.getMarketId(), markets);
            }
        });
    }

    public void setOnClickListener(final OnBuyProductClickedListener listener) {
        this.listener = listener;
    }

    private void bindMarketDialog(final Context context, final Long productId, final Long marketId, final List<Market> markets) {

        AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
        myDialog.setTitle("Comprar producto");
        myDialog.setMessage("Seleccione a que lista se agregará el producto.");

        final Spinner marketsSpinner = new Spinner(context);
        ArrayAdapter<Market> marketArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, markets);
        marketArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marketsSpinner.setAdapter(marketArrayAdapter);
        marketsSpinner.setPadding(toDp(context,16),0,toDp(context,16),0);
        marketsSpinner.setSelection(getMarketPosition(markets, marketId));
        myDialog.setView(marketsSpinner);

        myDialog.setPositiveButton("Comprar", (dialogInterface, i) -> AsyncTask.execute(() -> listener.onBuyProductClicked(productId, ((Market) marketsSpinner.getSelectedItem()).getId())));
        myDialog.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());
        myDialog.show();
    }

    // Extension method to convert pixels to dp
    private int toDp(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Long.valueOf(value), context.getResources().getDisplayMetrics());
    }

    private int getMarketPosition(List<Market> markets, Long marketId) {
        Integer foundIndex = null;
        for (int i = 0; i < markets.size(); i++) {
            if (markets.get(i).getId().equals(marketId)) {
                foundIndex = i;
                break;
            }
        }
        assert foundIndex != null;
        return foundIndex;
    }
}
