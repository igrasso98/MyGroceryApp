package ar.edu.itba.pam.mygrocery.home.ui;

import android.view.View;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;

public class OnAddProductConfirmlistener implements View.OnClickListener {

    private String name;
    private String description;
    private Long categoryId;
    private Market market;
    private final AddProductPresenter presenter;

    public OnAddProductConfirmlistener(AddProductPresenter presenter) {
        this.presenter = presenter;
    }

    public void onConfirm(View view, String name, String description, Long categoryId, Market market) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.market = market;

        onClick(view);
    }

    @Override
    public void onClick(View view) {
        presenter.onAddProductConfirm(name, description, categoryId, market);
    }
}
