package patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        Operations operations = new Operations();
        System.out.println(operations.sum(1));
        System.out.println(operations.multiply(2, 4));
        EventManager eventManager = operations.getEventManager();
        Observer simpleObserver1 = new SimpleObserver();
        eventManager.registerObserver(simpleObserver1);
        System.out.println(operations.sum(51, 79));
        System.out.println(operations.multiply(1000));
        eventManager.removeObserver(simpleObserver1);
        Observer simpleObserver2 = new SimpleObserver();
        eventManager.registerObserver(simpleObserver2);
        System.out.println(operations.sum(123213, 71239));
        System.out.println(operations.multiply(1,2,3,4,5,6));

        System.out.println(simpleObserver1.operations());
        System.out.println(simpleObserver2.operations());
    }
}

interface Observer {
    List<String> operations();
    void update(String event);
}

class SimpleObserver implements Observer {

    private final List<String> operations = new ArrayList<>();

    @Override
    public List<String> operations() {
        return operations;
    }

    @Override
    public void update(String event) {
        operations.add(event);
    }
}

class EventManager {
    private final List<Observer> observers = new ArrayList<>();

    void registerObserver(Observer observer) {
        observers.add(observer);
    }

    void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    void notifyObservers(String event) {
        observers.forEach(observer -> observer.update(event));
    }
}

class Operations {
    private final EventManager eventManager = new EventManager();

    int sum(int... args) {
        int result = Arrays.stream(args).sum();
        eventManager.notifyObservers("sum of " + Arrays.toString(args));
        return result;
    }

    int multiply(int... args) {
        int result = Arrays.stream(args).reduce(1, Math::multiplyExact);
        eventManager.notifyObservers("multiply of " + Arrays.toString(args));
        return result;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}