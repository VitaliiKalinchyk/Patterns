package patterns;

public class FactoryMethodPattern {
    public static void main(String[] args) {
        FactoryMethod firstFactory = new FirstFactory();
        FactoryMethod secondFactory = new SecondFactory();
        firstFactory.productExecutor();
        Product product1 = firstFactory.getProduct();
        Product product2 = secondFactory.getProduct();
        product1.method();
        product2.method();

        FactoryMethod.getProduct("first").method();
    }
}

interface FactoryMethod {

    default void productExecutor() {
        getProduct().method();
    }

    Product getProduct();

    static Product getProduct(String type){
        if (type.equals("first")) {
            return new FirstFactory().getProduct();
        } else {
            return new SecondFactory().getProduct();
        }
    }
}

class FirstFactory implements FactoryMethod {
    @Override
    public Product getProduct() {
        return new FirstProduct();
    }
}

class SecondFactory implements FactoryMethod {
    @Override
    public Product getProduct() {
        return new SecondProduct();
    }
}


interface Product {
    void method();
}

class FirstProduct implements Product {
    @Override
    public void method() {
        System.out.println("this is first product");
    }
}

class SecondProduct implements Product {
    @Override
    public void method() {
        System.out.println("this is second product");
    }
}