package ar.edu.itba.pam.mygrocery.home.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.ui.MarketsActivity;
import ar.edu.itba.pam.mygrocery.home.products.OnBuyProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.repository.RoomProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.ui.ProductsView;

public class HomeActivity extends AppCompatActivity implements HomeView, OnBuyProductClickedListener {
    private ProductsAdapter productsAdapter;
    private ProductsView productsView;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        createPresenter();
        setUpView();
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
        setUpProductsView();
    }

    private void setUpProductsView() {
        productsView = findViewById(R.id.products_activity);
        productsAdapter = new ProductsAdapter();
        productsAdapter.setOnBuyProductClickedListener(this);
        productsView.bind(productsAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void bindProducts(List<Category> model) {
        productsAdapter.setDataset(model);
        productsView.bind(productsAdapter);

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
    public void onBuyProductClicked(Long productId, Long marketId) {
        presenter.onBuyProductClicked(productId, marketId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.markets_item) {
            Intent intent = new Intent(this, MarketsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
