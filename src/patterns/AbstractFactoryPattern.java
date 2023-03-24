package patterns;

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory americanFactory = new AmericanFactory();
        americanFactory.getPasta().print();
        americanFactory.getPizza().print();
        AbstractFactory italianFactory = new ItalianFactory();
        italianFactory.getPasta().print();
        italianFactory.getPizza().print();
    }
}
abstract class Pizza {
    abstract void print();
}
class AmericanPizza extends Pizza {
    @Override
    void print() {
        System.out.println("This is american pizza");
    }
}
class ItalianPizza extends Pizza {
    @Override
    void print() {
        System.out.println("This is italian pizza");
    }
}
abstract class Pasta {
    abstract void print();
}
class AmericanPasta extends Pasta {
    @Override
    void print() {
        System.out.println("This is american pizza");
    }
}
class ItalianPasta extends Pasta {
    @Override
    void print() {
        System.out.println("This is italian pizza");
    }
}

interface AbstractFactory {
    Pizza getPizza();
    Pasta getPasta();
}
class AmericanFactory implements AbstractFactory {

    @Override
    public Pizza getPizza() {
        return new AmericanPizza();
    }

    @Override
    public Pasta getPasta() {
        return new AmericanPasta();
    }
}
class ItalianFactory implements AbstractFactory {

    @Override
    public Pizza getPizza() {
        return new ItalianPizza();
    }

    @Override
    public Pasta getPasta() {
        return new ItalianPasta();
    }
}