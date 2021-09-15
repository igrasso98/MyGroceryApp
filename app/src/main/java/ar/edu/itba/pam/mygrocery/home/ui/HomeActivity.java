package ar.edu.itba.pam.mygrocery.home.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.MarketAdapter;
import ar.edu.itba.pam.mygrocery.home.markets.OnMarketClickedListener;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.ui.MarketsView;
import ar.edu.itba.pam.mygrocery.home.products.OnBuyProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.repository.RoomProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.ui.ProductsView;

public class HomeActivity extends AppCompatActivity implements HomeView, OnBuyProductClickedListener, OnMarketClickedListener {

    private static final int PRODUCTS = 0;
    private static final int MARKETS = 1;

    private ViewSwitcher viewSwitcher;
    private ProductsAdapter productsAdapter;
    private MarketAdapter marketAdapter;
    private ProductsView productsView;
    private MarketsView marketsView;
    private BottomNavigationView navView;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        createPresenter();

        setUpView();
        setUpBottomNavigation();
    }

    private void createPresenter() {
        presenter = (HomePresenter) getLastNonConfigurationInstance();

        if (presenter == null) {
            final ProductMapper productMapper = new ProductMapper();
            final MarketMapper marketMapper = new MarketMapper();
            final ProductsRepository productsRepository = new RoomProductsRepository(MyGroceryDb.getInstance(getApplicationContext()).categoryProductsDao(), MyGroceryDb.getInstance(getApplicationContext()).categoryDao(), MyGroceryDb.getInstance(getApplicationContext()).productDao(), productMapper);
            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper);
            presenter = new HomePresenter(this, productsRepository, marketsRepository);
        }
    }

    private void setUpView() {
        viewSwitcher = findViewById(R.id.view_switcher);

        setUpProductsView();
        setUpMarketsView();
    }

    private void setUpProductsView() {
        productsView = findViewById(R.id.products_activity);
        productsAdapter = new ProductsAdapter();
        productsAdapter.setOnBuyProductClickedListener(this);
        productsView.bind(productsAdapter);
    }

    private void setUpMarketsView() {
        marketsView = findViewById(R.id.markets_activity);
        marketAdapter = new MarketAdapter();
        marketAdapter.setOnMarketClickedListener(this);
        marketsView.bind(marketAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setUpBottomNavigation() {
        navView = findViewById(R.id.bottom_navigation);
        navView.setSelectedItemId(R.id.products);

        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.products:
                    presenter.onProductsClicked();
                    return true;
                case R.id.markets:
                    presenter.onMarketsClicked();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void showProducts() {
        viewSwitcher.setDisplayedChild(PRODUCTS);
    }

    @Override
    public void showMarketProducts(Long marketId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("pam://markets/market"));
        intent.putExtra("market_id", marketId);
        startActivity(intent);
    }

    @Override
    public void showMarkets() {
        viewSwitcher.setDisplayedChild(MARKETS);
    }

    @Override
    public void bindProducts(List<Category> model) {
        productsAdapter.setDataset(model);
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

    @Override
    public void onBuyProductClicked(Long productId, Long marketId) {
        presenter.onBuyProductClicked(productId, marketId);
    }
}
