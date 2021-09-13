package ar.edu.itba.pam.mygrocery.home.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Map;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.product.ProductDb;
import ar.edu.itba.pam.mygrocery.home.products.OnProductClickedListener;
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.repository.RoomProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.ui.ProductsView;

public class HomeActivity extends AppCompatActivity implements HomeView, OnProductClickedListener {

    private static final int PRODUCTS = 0;
    private static final int MARKETS = 1;

    private ViewSwitcher viewSwitcher;
    private ProductsAdapter productsAdapter;
    private ProductsView productsView;
//    TODO: private MarketsView marketsView;
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
            final ProductsRepository productsRepository = new RoomProductsRepository(ProductDb.getInstance(getApplicationContext()).productDao(), productMapper);
            presenter = new HomePresenter(this, productsRepository);
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
        productsView.bind(productsAdapter);
    }

    private void setUpMarketsView() {
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
    public void showProductDetails(String id) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("pam://detail/demo")));
    }

    @Override
    public void showMarkets() {
        viewSwitcher.setDisplayedChild(MARKETS);
    }

    @Override
    public void bindProducts(Map<Category,List<Product>> model) {
        productsAdapter.setDataset(model);
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
    public void onClicked(final String id) {
        presenter.onProductClicked(id);
    }
}
