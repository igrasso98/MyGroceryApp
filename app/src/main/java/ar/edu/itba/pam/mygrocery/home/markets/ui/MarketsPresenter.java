package ar.edu.itba.pam.mygrocery.home.markets.ui;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MarketsPresenter {
    private final MarketsRepository marketsRepository;

    private final WeakReference<MarketsView> view;

    private Disposable marketsDisposable;

    public MarketsPresenter(final MarketsView view, final MarketsRepository marketsRepository) {
        this.view = new WeakReference<>(view);
        this.marketsRepository = marketsRepository;
    }

    public void onMarketClicked(final Long marketId) {
        if (view.get() != null) {
            view.get().showMarketProducts(marketId);
        }
    }

    public void onViewAttached() {
        marketsDisposable = marketsRepository.getProductsByMarket().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onMarketsReceived);
    }

    private void onMarketsReceived(final List<Market> model) {
        if (view.get() != null) {
            final List<Market> markets = removeEmptyMarkets(model);
            view.get().bindMarkets(markets);
        }
    }

    private List<Market> removeEmptyMarkets(List<Market> model) {
        List<Market> markets = new ArrayList<>();
        for (Market market : model) {
            if (market.getProducts().size() > 0) {
                markets.add(market);
            }
        }
        return markets;
    }

    public void onViewDetached() {
        marketsDisposable.dispose();
    }

}
