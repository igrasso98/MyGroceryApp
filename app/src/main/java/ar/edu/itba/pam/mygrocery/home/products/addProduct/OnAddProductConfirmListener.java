package ar.edu.itba.pam.mygrocery.home.products.addProduct;

public interface OnAddProductConfirmListener {

    void onConfirm(String name, String description, Long categoryId, Long marketId, int autorestock);
}
