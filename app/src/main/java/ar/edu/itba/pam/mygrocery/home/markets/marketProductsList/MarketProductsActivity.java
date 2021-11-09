package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.MarketAdapter;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.ui.MarketProductsListView;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.OnBuyProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;

public class MarketProductsActivity extends AppCompatActivity implements MarketProductsView, OnCheckProductClickedListener {
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
            final ProductMapper productMapper = new ProductMapper();
            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper, productMapper);
            presenter = new MarketProductsPresenter(this, marketsRepository);
        }
    }

    private void setUpView() {
        marketProductsListView = findViewById(R.id.market_products_list);
        marketProductAdapter = new MarketProductAdapter();
        marketProductAdapter.setOnProductClickedListener(this);
        marketProductsListView.bind(marketProductAdapter);
    }

    @Override
    public void bind(List<Product> products) {
        marketProductAdapter.setDataset(products);
    }

    @Override
    public void setActivityTitle(String marketName) {
        setTitle(marketName);
    }

    @Override
    protected void onStart() {
        Intent intent = getIntent();
        super.onStart();
        presenter.onViewAttached(intent.getLongExtra("market_id", -1), intent.getStringExtra("market_name"));
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

    @Override
    public void onCheckProduct(Long marketProductId, Boolean check) {
        presenter.onCheckProductClicked(marketProductId, check);

    }
}
