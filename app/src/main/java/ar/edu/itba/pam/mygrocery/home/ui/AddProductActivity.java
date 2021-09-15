package ar.edu.itba.pam.mygrocery.home.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

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
import ar.edu.itba.pam.mygrocery.home.products.ProductsAdapter;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductsRepository;
import ar.edu.itba.pam.mygrocery.home.products.repository.RoomProductsRepository;

public class AddProductActivity extends AppCompatActivity implements AddProductView {
    private AddProductPresenter presenter;
    private AddProductFormView addProductFormView;
    private OnAddProductConfirmlistener onAddProductConfirmlistener;
    private OnAddProductCancelListener onAddProductCancelListener;


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
            onAddProductConfirmlistener = new OnAddProductConfirmlistener(presenter);
            onAddProductCancelListener = new OnAddProductCancelListener(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(AddProductActivity.this, HomeActivity.class));
                }
            });

        }
    }

    private void setUpView() {


        addProductFormView = findViewById(R.id.add_product_form);
        addProductFormView.bind(new ArrayList<>(), new ArrayList<>(), onAddProductConfirmlistener, onAddProductCancelListener);
    }

    @Override
    public void bind(List<Market> markets, List<Category> categories) {
        addProductFormView.bind(markets, categories, onAddProductConfirmlistener, onAddProductCancelListener);
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
}
