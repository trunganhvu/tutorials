package FactoryPattern.BeanCase;

public class ProductFactory {
    public Product createProduct() {
        // Logic to create and configure the product
        return new Product("Product Name");
    }
}
