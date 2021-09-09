package ar.edu.itba.pam.mygrocery.home.ui;

import java.lang.ref.WeakReference;
import java.util.List;

import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter {
    private final ProductsRepository productsRepository;

    private final WeakReference<HomeView> view;

    private Disposable disposable;

    public HomePresenter(final HomeView view, final ProductsRepository repository) {
        this.view = new WeakReference<>(view);
        this.productsRepository = repository;
    }

    public void onProductClicked(final String id) {
        if(view.get() != null) {
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
        disposable = productsRepository.getProducts().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onProductsReceived);
    }

    private void onProductsReceived(final List<Product> model) {
        if (view.get() != null) {
            view.get().bindProducts(model);
        }
    }

    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        disposable.dispose();
    }

}
