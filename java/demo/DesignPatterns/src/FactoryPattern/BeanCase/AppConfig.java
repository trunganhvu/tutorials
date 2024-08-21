package FactoryPattern.BeanCase;

public class AppConfig {
    private static ProductFactory productFactoryInstance;
    private static Product productInstance;

    public static ProductFactory getProductFactory() {
        if (productFactoryInstance == null) {
            productFactoryInstance = new ProductFactory();
        }
        return productFactoryInstance;
    }

    public static Product getProduct() {
        if (productInstance == null) {
            productInstance = getProductFactory().createProduct();
        }
        return productInstance;
    }
}
