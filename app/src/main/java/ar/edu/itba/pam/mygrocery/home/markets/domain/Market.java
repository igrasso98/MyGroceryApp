package ar.edu.itba.pam.mygrocery.home.markets.domain;

import java.util.List;

import ar.edu.itba.pam.mygrocery.home.products.domain.Product;

public class Market {
    private final Long id;
    private final String name;
    private final int productsQty;

    public Market(final Long id, final String name, final int productsQty) {
        this.id = id;
        this.name = name;
        this.productsQty = productsQty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProductsQty() {
        return productsQty;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Market))
            return false;
        Market cat = (Market) o;
        return cat.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
