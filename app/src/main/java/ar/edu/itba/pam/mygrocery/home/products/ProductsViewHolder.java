package ar.edu.itba.pam.mygrocery.home.products;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ar.edu.itba.pam.mygrocery.R;
import ar.edu.itba.pam.mygrocery.home.products.domain.Category;
import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

// RESPONSABILIDAD DE REPRESENTAR LOS DATOS EN LA PANTALLA
public class ProductsViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView productsByCategory;



    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        productsByCategory = itemView.findViewById(R.id.products_by_category);
    }

    public void bind(final Category model) {
        final TextView productCategoryName = itemView.findViewById(R.id.product_category_name);
        productCategoryName.setText(model.getName());
        final TextView productCategoryDescription = itemView.findViewById(R.id.product_category_description);
        productCategoryDescription.setText(model.getProducts().size() + " Productos");
        if (model.getImage() != null) {
            final ImageView productCategoryImage = itemView.findViewById(R.id.product_category_image);
            Bitmap bmp = BitmapFactory.decodeByteArray(model.getImage(), 0, model.getImage().length);
            productCategoryImage.setImageBitmap(bmp);
        }

    }
}
