package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.MarketAdapter;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.ui.MarketProductsListView;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;

public class MarketProductsActivity extends AppCompatActivity implements MarketProductsView {
    private MarketProductAdapter marketProductAdapter;
    private MarketProductsPresenter presenter;
    private MarketProductsListView marketProductsListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        setContentView(R.layout.market_products_list_activity);
        createPresenter();

        setUpView();
    }

    private void createPresenter() {
        presenter = (MarketProductsPresenter) getLastNonConfigurationInstance();

        if (presenter == null) {
            final MarketMapper marketMapper = new MarketMapper();
            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper);
            presenter = new MarketProductsPresenter(this, marketsRepository);
        }
    }

    private void setUpView() {
        marketProductsListView = findViewById(R.id.market_products_list);
        marketProductAdapter = new MarketProductAdapter();
        marketProductsListView.bind(marketProductAdapter);
    }

    @Override
    public void bind(Market market) {
        marketProductAdapter.setDataset(market.getProducts());
    }

    @Override
    public void setActivityTitle(String marketName) {
        setTitle(marketName);
    }

    @Override
    protected void onStart() {
        Intent intent = getIntent();
        super.onStart();
        presenter.onViewAttached(intent.getLongExtra("market_id", -1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
