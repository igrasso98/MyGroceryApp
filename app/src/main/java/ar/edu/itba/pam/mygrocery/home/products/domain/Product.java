package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Product {
    private final String name;

    public Product(final String name) {
        this.name = name;
    }

    public String getProductName() {
        return name;
    }
}
