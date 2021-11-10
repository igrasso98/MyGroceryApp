package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import java.lang.ref.WeakReference;
import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MarketProductsPresenter {
    private final MarketsRepository marketsRepository;
    private final WeakReference<MarketProductsView> view;

    private Disposable disposable;

    public MarketProductsPresenter(final MarketProductsView view, final MarketsRepository marketsRepository) {
        this.view = new WeakReference<>(view);
        this.marketsRepository = marketsRepository;
    }

    public void onViewAttached(final Long marketId, final String marketName) {
        disposable = marketsRepository.getMarketProductsList(marketId).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(p -> this.onProductsListReceived(p,marketName));
    }

    private void onProductsListReceived(final List<Product> products, String marketName) {
        if (view.get() != null) {
            view.get().setActivityTitle(marketName);
            view.get().bind(products);
        }
    }

    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        disposable.dispose();
    }

    public void onCheckProductClicked(Long marketProductId, Boolean check) {
        marketsRepository.checkProduct(marketProductId, check);
    }

    public void onCloseListClicked(Long marketId) {
        marketsRepository.closeMarketProductsList(marketId);
    }
}
