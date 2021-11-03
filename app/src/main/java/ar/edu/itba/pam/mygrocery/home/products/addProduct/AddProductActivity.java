package ar.edu.itba.pam.mygrocery.home.products.addProduct;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.ui.AddProductFormView;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.repository.RoomProductsRepository;

public class AddProductActivity extends AppCompatActivity implements AddProductView, OnAddProductConfirmListener, OnAddProductCancelListener, OnCreateCategoryListener, OnCreateMarketListener {
    private AddProductPresenter presenter;
    private AddProductFormView addProductFormView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);
        createPresenter();

        setUpView();
    }

    private void createPresenter() {
        presenter = (AddProductPresenter) getLastNonConfigurationInstance();

        if (presenter == null) {
            final ProductMapper productMapper = new ProductMapper();
            final MarketMapper marketMapper = new MarketMapper();
            final ProductsRepository productsRepository = new RoomProductsRepository(MyGroceryDb.getInstance(getApplicationContext()).categoryProductsDao(), MyGroceryDb.getInstance(getApplicationContext()).categoryDao(), MyGroceryDb.getInstance(getApplicationContext()).productDao(), productMapper);
            final MarketsRepository marketsRepository = new RoomMarketsRepository(MyGroceryDb.getInstance(getApplicationContext()).marketDao(), MyGroceryDb.getInstance(getApplicationContext()).marketProductsDao(), marketMapper);
            presenter = new AddProductPresenter(this, productsRepository, marketsRepository);
        }
    }

    private void setUpView() {
        addProductFormView = findViewById(R.id.add_product_form);
        addProductFormView.setOnAddProductConfirmListener(this);
        addProductFormView.setOnAddProductCancelListener(this);
        addProductFormView.setOnCreateCategoryListener(this);
        addProductFormView.setOnCreateMarketListener(this);
        addProductFormView.bind(new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public void bind(List<Market> markets, List<Category> categories) {
        addProductFormView.bind(markets, categories);
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
    public void onCancel() {
        finish();
    }

    @Override
    public void onConfirm(String name, String description, Long categoryId, Long marketId) {
        presenter.onAddProductConfirm(name, description, categoryId, marketId);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onCreateCategory(String name) {
        presenter.onCreateCategory(name);
    }

    @Override
    public void onCreateMarket(String name) {
        presenter.onCreateMarket(name);
    }
}
