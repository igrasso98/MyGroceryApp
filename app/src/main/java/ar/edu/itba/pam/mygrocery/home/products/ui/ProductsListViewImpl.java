package ar.edu.itba.pam.mygrocery.home.products.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;

public class ProductsListViewImpl extends RecyclerView implements ProductsListView {
    public ProductsListViewImpl(@NonNull Context context) {
        super(context);
    }

    public ProductsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductsListViewImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void bind(ProductsAdapter productsAdapter) {
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (productsAdapter.getItemCount() == 0) {
            setBackgroundResource(R.drawable.ic_empty__2_);
        } else {
            setBackgroundResource(R.color.white);
        }
        setAdapter(productsAdapter);

    }
}
