package ar.edu.itba.pam.mygrocery.home.products.addProduct.ui;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.START;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.markets.domain.Market;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductConfirmListener;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.addProduct.OnAddProductCancelListener;

public class AddProductFormImpl extends LinearLayout implements AddProductFormView {

    private OnAddProductConfirmListener onAddProductConfirmListener;
    private OnAddProductCancelListener onAddProductCancelListener;

    private final EditText nameEditText;
    private final EditText descriptionEditText;
    private final Spinner categoriesSpinner;
    private final Spinner marketsSpinner;
    private final FloatingActionButton confirmBtn;
    private final FloatingActionButton cancelBtn;

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
        nameEditText = findViewById(R.id.enter_product_name);
        descriptionEditText = findViewById(R.id.enter_product_description);
        categoriesSpinner = findViewById(R.id.choose_category);
        marketsSpinner = findViewById(R.id.choose_market);
        confirmBtn = findViewById(R.id.confirm_product_button);
        cancelBtn = findViewById(R.id.cancel_product_button);
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
                        AsyncTask.execute(() -> onAddProductConfirmListener.onConfirm(nameEditText.getText().toString(), descriptionEditText.getText().toString(), ((Category) categoriesSpinner.getSelectedItem()).getId(), ((Market) marketsSpinner.getSelectedItem()).getId()));
                        cancelBtn.performClick();
                    }
                }
        );

        cancelBtn.setOnClickListener(v -> onAddProductCancelListener.onCancel());
    }

    @Override
    public void setOnAddProductConfirmListener(OnAddProductConfirmListener listener) {
        this.onAddProductConfirmListener = listener;
    }

    @Override
    public void setOnAddProductCancelListener(OnAddProductCancelListener listener) {
        this.onAddProductCancelListener = listener;
    }
}
