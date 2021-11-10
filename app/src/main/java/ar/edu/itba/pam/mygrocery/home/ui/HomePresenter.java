package ar.edu.itba.pam.mygrocery.home.ui;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter {
    private final ProductsRepository productsRepository;
    private final MarketsRepository marketsRepository;

    private final WeakReference<HomeView> view;

    private Disposable productsDisposable;
    private Disposable marketsDisposable;

    public HomePresenter(final HomeView view, final ProductsRepository productsRepository, final MarketsRepository marketsRepository) {
        this.view = new WeakReference<>(view);
        this.productsRepository = productsRepository;
        this.marketsRepository = marketsRepository;
    }

    public void onBuyProductClicked(final Long productId, final Long marketId) {
        // TODO: Open dialog, ask qty and confirm purchase
        marketsRepository.buyProduct(productId, marketId);
    }

    public void onViewAttached() {
        productsDisposable = productsRepository.getProductsByCategory().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onProductsReceived);
        marketsDisposable = marketsRepository.getMarkets().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onMarketsReceived);
    }

    private void onProductsReceived(final List<Category> model) {
        if (view.get() != null) {
            final List<Category> categories = removeEmptyCategories(model);
            view.get().bindProducts(categories);
        }
    }

    private void onMarketsReceived(final List<Market> model) {
        if (view.get() != null) {
            view.get().addMarkets(model);
        }
    }

    private List<Category> removeEmptyCategories(List<Category> model) {
        List<Category> categories = new ArrayList<>();
        for(Category category : model) {
            if(category.getProducts().size() > 0)
                categories.add(category);
        }
        return categories;
    }

    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        productsDisposable.dispose();
    }

}
