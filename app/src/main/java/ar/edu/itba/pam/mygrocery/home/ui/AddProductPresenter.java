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

public class AddProductPresenter {
    private final ProductsRepository productsRepository;
    private final MarketsRepository marketsRepository;
    private final WeakReference<AddProductView> view;

    private Disposable marketsDisposable;
    private Disposable categoriesDisposable;

    public AddProductPresenter(final AddProductView view, final ProductsRepository productsRepository, final MarketsRepository marketsRepository) {
        this.view = new WeakReference<>(view);
        this.productsRepository = productsRepository;
        this.marketsRepository = marketsRepository;
    }

    public void onViewAttached() {
        categoriesDisposable = productsRepository.getCategories().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onCategoriesReceived);
    }

    private void onMarketsReceived(final List<Market> markets, List<Category> categories) {
        if (view.get() != null) {
            view.get().bind(markets, categories);
        }
    }

    private void onCategoriesReceived(final List<Category> model) {
        marketsDisposable = marketsRepository.getMarkets().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(markets -> onMarketsReceived(markets, model));
    }


    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        marketsDisposable.dispose();
    }

    public void onAddProductConfirm(String name, String description, Long categoryId, Long marketId) {
        productsRepository.addProduct(new Product(name, description, categoryId, marketId));
    }

}
