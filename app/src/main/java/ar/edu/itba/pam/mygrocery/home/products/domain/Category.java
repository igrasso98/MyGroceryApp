package ar.edu.itba.pam.mygrocery.home.products.domain;

import java.util.List;

public class Category {
    private final String name;

    private final byte[] image;

    private final List<Product> products;

    public Category(final String name, final byte[] image, final List<Product> products) {
        this.name = name;
        this.image = image;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category))
            return false;
        Category cat = (Category) o;
        return cat.name.equals(name);
    }
}
