package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Product {
    private final String name;
    private final String description;
    private final Category category;


    public Product(final String name, final String description, final Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Product(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.category = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
