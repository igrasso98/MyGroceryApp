package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final Long categoryId;
    private final Long marketId;
    private final Boolean isChecked;
    private final Long marketProductId;


    public Product(final Long id, final String name, final String description, final Long categoryId, final Long marketId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = null;
        this.marketProductId = null;
    }

    public Product(final String name, final String description, final Long categoryId, final Long marketId) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = null;
        this.marketProductId = null;
    }

    public Product(final Long id,final String name, final String description, final Long categoryId, final Long marketId, final Boolean isChecked, final Long marketProductId) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = isChecked;
        this.marketProductId = marketProductId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public Long getMarketProductId() {
        return marketProductId;
    }
}
