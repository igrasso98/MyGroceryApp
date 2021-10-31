package ar.edu.itba.pam.mygrocery.home.markets.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.MarketAdapter;
import ar.edu.itba.pam.mygrocery.home.markets.OnMarketClickedListener;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.MarketProductsActivity;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;

public class MarketsActivity extends AppCompatActivity implements MarketsView, OnMarketClickedListener {
    private MarketAdapter marketAdapter;
    private MarketsListView marketsListView;

    private MarketsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markets_activity);
        createPresenter();
        setUpView();
    }

    private void createPresenter() {
        presenter = (MarketsPresenter) getLastNonConfigurationInstance();

        if (presenter == null) {
            final MarketMapper marketMapper = new MarketMapper();

            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper);
            presenter = new MarketsPresenter(this, marketsRepository);
        }
    }

    private void setUpView() {
        setUpMarketsView();
    }

    private void setUpMarketsView() {
        marketsListView = findViewById(R.id.markets_list_view);
        marketAdapter = new MarketAdapter();
        marketAdapter.setOnMarketClickedListener(this);
        marketsListView.bind(marketAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void showMarketProducts(Long marketId) {
        Intent intent = new Intent(this, MarketProductsActivity.class);
        intent.setData(Uri.parse("pam://markets/market"));
        intent.putExtra("market_id", marketId);
        startActivity(intent);
    }

    @Override
    public void bindMarkets(List<Market> model) {
        marketAdapter.setDataset(model);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public void onMarketClicked(final Long marketId) {
        presenter.onMarketClicked(marketId);
    }
}