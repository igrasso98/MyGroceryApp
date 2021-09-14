package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final Long categoryId;



    public Product(final Long id,final String name, final String description, final Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }
    public Product(final String name, final String description, final Long categoryId) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Long getId() {return  id;}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

}
