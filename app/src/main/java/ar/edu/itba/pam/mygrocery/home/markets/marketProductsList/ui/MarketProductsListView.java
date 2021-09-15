package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.ui;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.MarketProductAdapter;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.ui.OnAddProductCancelListener;
import ar.edu.itba.pam.mygrocery.home.ui.OnAddProductConfirmlistener;

public interface MarketProductsListView {
    void bind(MarketProductAdapter marketProductAdapter);
}
