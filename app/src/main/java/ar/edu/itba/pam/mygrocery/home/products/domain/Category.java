package ar.edu.itba.pam.mygrocery.home.products.domain;

public class Category {
    private final String name;

    private final byte[] image;

    public Category(final String name, final byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
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
