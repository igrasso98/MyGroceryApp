package ar.edu.itba.pam.mygrocery.home.products.ui;

import static android.view.Gravity.CENTER;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private final ProductsListViewImpl productsListView;
    private final FloatingActionButton floatingActionButton;

    public ProductsViewImpl(@NonNull Context context) {
        this(context, null);
    }

    public ProductsViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductsViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.products_activity, this);
        setGravity(CENTER);
        productsListView = findViewById(R.id.products_list);
        floatingActionButton = findViewById(R.id.add_product_button);

    }

    @Override
    public void bind(ProductsAdapter productsAdapter) {
        productsListView.bind(productsAdapter);
        floatingActionButton.setOnClickListener(v -> getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("pam://products/create"))));
    }
}
