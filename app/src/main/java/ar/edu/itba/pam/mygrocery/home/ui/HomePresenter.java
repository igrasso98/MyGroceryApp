package ar.edu.itba.pam.mygrocery.home.ui;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
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

    public void onProductClicked(final String id) {
        if (view.get() != null) {
            view.get().showProductDetails(id);
        }
    }

    public void onProductsClicked() {
        if (view.get() != null) {
            view.get().showProducts();
        }
    }

    public void onMarketsClicked() {
        if (view.get() != null) {
            view.get().showMarkets();
        }
    }

    public void onViewAttached() {
        productsDisposable = productsRepository.getProductsByCategory().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onProductsReceived);
        marketsDisposable = marketsRepository.getProductsByMarket().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onMarketsReceived);
    }

    private void onProductsReceived(final List<Category> model) {
        if (view.get() != null) {
            view.get().bindProducts(model);
        }
    }

    private void onMarketsReceived(final List<Market> model) {
        if (view.get() != null) {
            view.get().bindMarkets(model);
        }
    }

    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        productsDisposable.dispose();
        marketsDisposable.dispose();
    }

}
