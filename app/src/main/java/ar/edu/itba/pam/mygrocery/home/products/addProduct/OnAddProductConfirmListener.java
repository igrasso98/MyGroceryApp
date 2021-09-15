package ar.edu.itba.pam.mygrocery.home.products.addProduct;

import android.view.View;

import ar.edu.itba.pam.mygrocery.home.products.addProduct.AddProductPresenter;

public interface OnAddProductConfirmListener {

    void onConfirm( String name, String description, Long categoryId, Long marketId);
}
