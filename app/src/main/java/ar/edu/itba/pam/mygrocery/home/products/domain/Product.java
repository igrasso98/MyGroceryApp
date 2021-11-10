package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final Long categoryId;
    private final Long marketId;
    private final Boolean isChecked;
    private final Long marketProductId;
    private final Integer autorestock;


    public Product(final Long id, final String name, final String description, final Long categoryId, final Long marketId, final Integer autorestock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = null;
        this.marketProductId = null;
        this.autorestock = autorestock;
    }

    public Product(final String name, final String description, final Long categoryId, final Long marketId, final Integer autorestock) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = null;
        this.marketProductId = null;
        this.autorestock = autorestock;
    }

    public Product(final Long id,final String name, final String description, final Long categoryId, final Long marketId, final Boolean isChecked, final Long marketProductId, final Integer autorestock) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.marketId = marketId;
        this.isChecked = isChecked;
        this.marketProductId = marketProductId;
        this.autorestock = autorestock;
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
