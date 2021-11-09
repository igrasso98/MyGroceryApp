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
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;

public class MarketsActivity extends AppCompatActivity implements MarketsView, OnMarketClickedListener {
    private MarketAdapter marketAdapter;
    private MarketsListView marketsListView;

    private MarketsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        setContentView(R.layout.markets_activity);
        createPresenter();
        setUpView();
    }

    private void createPresenter() {
        presenter = (MarketsPresenter) getLastNonConfigurationInstance();

        if (presenter == null) {
            final MarketMapper marketMapper = new MarketMapper();
            final ProductMapper productMapper = new ProductMapper();

            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper, productMapper);
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
    public void showMarketProducts(Long marketId, String name) {
        Intent intent = new Intent(this, MarketProductsActivity.class);
        intent.setData(Uri.parse("pam://markets/market"));
        intent.putExtra("market_id", marketId);
        intent.putExtra("market_name", name);
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
    public void onMarketClicked(final Long marketId, final String name) {
        presenter.onMarketClicked(marketId, name);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
