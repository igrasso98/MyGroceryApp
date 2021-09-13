package ar.edu.itba.pam.mygrocery.home.products.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;

public class ProductsViewImpl extends RelativeLayout implements ProductsView {

    public ProductsViewImpl(@NonNull Context context) {
        super(context);
    }

    public ProductsViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductsViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bind(ProductsAdapter productsAdapter) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.products_activity, this, false);
        ProductsListViewImpl productsListView = view.findViewById(R.id.products_list);
        productsListView.bind(productsAdapter);
        FloatingActionButton addProductButton = view.findViewById(R.id.add_product_button);
        addView(view);
    }
}
