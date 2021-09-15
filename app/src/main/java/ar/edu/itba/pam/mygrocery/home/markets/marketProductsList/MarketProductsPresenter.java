package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import java.lang.ref.WeakReference;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
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

    public void onViewAttached(final Long marketId) {
        disposable = marketsRepository.getMarketProductsList(marketId).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onProductsListReceived);
    }

    private void onProductsListReceived(final Market market) {
        if (view.get() != null) {
            view.get().setActivityTitle(market.getName());
            view.get().bind(market);
        }
    }

    private void onProductsError(final Throwable e) {
        //Explain the error to the user
    }

    public void onViewDetached() {
        disposable.dispose();
    }

}
