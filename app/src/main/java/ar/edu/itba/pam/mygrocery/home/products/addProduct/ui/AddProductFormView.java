package ar.edu.itba.pam.mygrocery.home.products.addProduct.ui;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductCancelListener;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductConfirmListener;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnCreateCategoryListener;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnCreateMarketListener;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;

public interface AddProductFormView {
    void bind(List<Market> markets, List<Category> categories);

    void setOnAddProductConfirmListener(OnAddProductConfirmListener listener);

    void setOnAddProductCancelListener(OnAddProductCancelListener listener);

    void setOnCreateCategoryListener(OnCreateCategoryListener listener);

    void setOnCreateMarketListener(OnCreateMarketListener listener);
}
