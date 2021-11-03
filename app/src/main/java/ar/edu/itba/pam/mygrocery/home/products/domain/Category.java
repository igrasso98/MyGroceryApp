package ar.edu.itba.pam.mygrocery.home.products.domain;

import java.util.List;

public class Category {

    private final Long id;

    private final String name;

    private final List<Product> products;

    public Category(final Long id, final String name, final List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return name;
    }
}
