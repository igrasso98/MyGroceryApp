package ar.edu.itba.pam.mygrocery.home.markets.marketProductsList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.db.MyGroceryDb;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.markets.marketProductsList.ui.MarketProductsListView;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketMapper;
import ar.edu.itba.pam.mygrocery.home.markets.repository.MarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.repository.RoomMarketsRepository;
import ar.edu.itba.pam.mygrocery.home.markets.ui.MarketsActivity;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;
import ar.edu.itba.pam.mygrocery.home.products.repository.ProductMapper;

public class MarketProductsActivity extends AppCompatActivity implements MarketProductsView, OnCheckProductClickedListener, OnCloseListClickedListener {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_navigation_market_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete_item) {
            bindCloseDialog(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCloseListClicked(Long marketId) {
        presenter.onCloseListClicked(marketId);
        onSupportNavigateUp();
    }

    private void bindCloseDialog(final Context context) {
        Intent intent = getIntent();

        AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
        myDialog.setTitle("Cerrar lista");
        myDialog.setMessage("Se quitaran los productos de la misma");

        myDialog.setPositiveButton("Cerrar lista", (dialogInterface, i) -> AsyncTask.execute(() -> onCloseListClicked(intent.getLongExtra("market_id", -1))));
        myDialog.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());
        myDialog.show();
    }
}
