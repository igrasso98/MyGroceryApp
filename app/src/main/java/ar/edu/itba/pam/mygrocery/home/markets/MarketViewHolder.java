package ar.edu.itba.pam.mygrocery.home.markets;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;

public class MarketViewHolder extends RecyclerView.ViewHolder {
    private OnMarketClickedListener listener;

    public MarketViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(final Market model) {
        final TextView marketName = itemView.findViewById(R.id.market_name);
        marketName.setText(model.getName());
        final TextView marketDescription = itemView.findViewById(R.id.market_total_products);
        marketDescription.setText(model.getProducts().size() + " Productos");

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMarketClicked(model.getId());
            }
        });
    }

    public void setOnClickListener(final OnMarketClickedListener listener) {
        this.listener = listener;
    }
}
