package ar.edu.itba.pam.mygrocery.home.ui;

import android.view.View;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;

public interface AddProductFormView {
    void bind(List<Market> markets, List<Category> categories, final OnAddProductConfirmlistener onClick, final OnAddProductCancelListener onAddProductCancelListener);
}
