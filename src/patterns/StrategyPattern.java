package patterns;

public class StrategyPattern {
    public static void main(String[] args) {
        MyConcreteClass myConcreteClass = new MyConcreteClass();
        myConcreteClass.executeStrategy("bat");
        Strategy strategy = new ReverseStrategy();
        myConcreteClass.setStrategy(strategy);
        myConcreteClass.executeStrategy("bat");
        strategy = new RepeatStrategy();
        myConcreteClass.setStrategy(strategy);
        myConcreteClass.executeStrategy("bat");
    }
}

abstract class MyAbstractClass {
    private Strategy strategy;

    public void executeStrategy(String word) {
        strategy.execute(word);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}

class MyConcreteClass extends MyAbstractClass {
    public MyConcreteClass() {
        setStrategy(new BaseStrategy());
    }
}

interface Strategy {
    void execute(String word);
}

class BaseStrategy implements Strategy {
    @Override
    public void execute(String word) {
        System.out.println(word);
    }
}

class ReverseStrategy implements Strategy {
    @Override
    public void execute(String word) {
        System.out.println(new StringBuilder(word).reverse());
    }
}

class RepeatStrategy implements Strategy {
    @Override
    public void execute(String word) {
        System.out.println(word.repeat(2));
    }
}