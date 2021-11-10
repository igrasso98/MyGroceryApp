package ar.edu.itba.pam.mygrocery.home.products.addProduct.ui;

import static android.view.Gravity.END;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductConfirmListener;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnCreateCategoryListener;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnCreateMarketListener;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductCancelListener;

public class AddProductFormImpl extends LinearLayout implements AddProductFormView {

    private OnAddProductConfirmListener onAddProductConfirmListener;
    private OnAddProductCancelListener onAddProductCancelListener;
    private OnCreateMarketListener onCreateMarketListener;
    private OnCreateCategoryListener onCreateCategoryListener;


    private int autorestock;
    private final EditText nameEditText;
    private final TextView autorestockText;
    private final EditText descriptionEditText;
    private final Spinner categoriesSpinner;
    private final Spinner marketsSpinner;
    private final FloatingActionButton confirmBtn;
    private final FloatingActionButton cancelBtn;
    private final FloatingActionButton autorestockMinus;
    private final FloatingActionButton autorestockPlus;
    private final FloatingActionButton createMarketBtn;
    private final FloatingActionButton createCategoryBtn;

    public AddProductFormImpl(@NonNull Context context) {
        this(context, null);
    }

    public AddProductFormImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddProductFormImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.add_product_form, this);
        setGravity(END);
        autorestockText = findViewById(R.id.autorestock_days);
        nameEditText = findViewById(R.id.enter_product_name);
        descriptionEditText = findViewById(R.id.enter_product_description);
        categoriesSpinner = findViewById(R.id.choose_category);
        marketsSpinner = findViewById(R.id.choose_market);
        confirmBtn = findViewById(R.id.confirm_product_button);
        cancelBtn = findViewById(R.id.cancel_product_button);
        autorestockMinus = findViewById(R.id.counter_minus);
        autorestockPlus = findViewById(R.id.counter_plus);
        createMarketBtn = findViewById(R.id.add_market_button);
        createCategoryBtn = findViewById(R.id.add_category_button);
    }

    @Override
    public void bind(final List<Market> markets, final List<Category> categories) {
        ArrayAdapter<Market> marketArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, markets);
        marketArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marketsSpinner.setAdapter(marketArrayAdapter);

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(categoryArrayAdapter);

        confirmBtn.setOnClickListener(v -> {
                    if ((!(nameEditText.getText().toString().isEmpty() && descriptionEditText.getText().toString().isEmpty()))) {
                        AsyncTask.execute(() -> onAddProductConfirmListener.onConfirm(nameEditText.getText().toString(), descriptionEditText.getText().toString(), ((Category) categoriesSpinner.getSelectedItem()).getId(), ((Market) marketsSpinner.getSelectedItem()).getId(), autorestock));
                        cancelBtn.performClick();
                    }
                }
        );

        cancelBtn.setOnClickListener(v -> onAddProductCancelListener.onCancel());

        autorestockMinus.setOnClickListener(v -> setAutorestock(autorestock - 1));
        autorestockPlus.setOnClickListener(v -> setAutorestock(autorestock + 1));

        createMarketBtn.setOnClickListener(v -> bindMarketDialog());
        createCategoryBtn.setOnClickListener(v -> bindCategoryDialog());
    }

    @Override
    public void setOnAddProductConfirmListener(OnAddProductConfirmListener listener) {
        this.onAddProductConfirmListener = listener;
    }

    @Override
    public void setOnAddProductCancelListener(OnAddProductCancelListener listener) {
        this.onAddProductCancelListener = listener;
    }

    @Override
    public void setOnCreateCategoryListener(OnCreateCategoryListener listener) {
        this.onCreateCategoryListener = listener;
    }

    @Override
    public void setOnCreateMarketListener(OnCreateMarketListener listener) {
        this.onCreateMarketListener = listener;
    }

    private void setAutorestock(int qty) {
        if (qty <= 0) {
            this.autorestock = 0;
            this.autorestockText.setText("Desactivado");
            this.autorestockMinus.setEnabled(false);
        } else {
            this.autorestock = qty;
            this.autorestockText.setText(qty + (qty == 1 ? " dia" : " dias"));
            if (!this.autorestockMinus.isEnabled()) {
                this.autorestockMinus.setEnabled(true);
            }
        }
    }

    private void bindMarketDialog() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(getContext());
        myDialog.setTitle("Agregar Mercado");

        final EditText marketNameInput = new EditText(getContext());
        myDialog.setView(marketNameInput);

        myDialog.setPositiveButton("Agregar", (dialogInterface, i) -> AsyncTask.execute(() -> onCreateMarketListener.onCreateMarket(marketNameInput.getText().toString())));

        myDialog.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());
        myDialog.show();
    }

    private void bindCategoryDialog() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(getContext());
        myDialog.setTitle("Agregar Categoria");

        final EditText categoryNameInput = new EditText(getContext());
        myDialog.setView(categoryNameInput);

        myDialog.setPositiveButton("Agregar", (dialogInterface, i) -> AsyncTask.execute(() -> onCreateCategoryListener.onCreateCategory(categoryNameInput.getText().toString())));

        myDialog.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());
        myDialog.show();
    }
}
