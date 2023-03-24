package patterns;

public class BridgePattern {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car(new Produce(), new Assemble());
        vehicle1.manufacture();
        System.out.println();
        Vehicle vehicle2 = new Car(new Assemble(), new Produce());
        vehicle2.manufacture();
    }
}

abstract class Vehicle {
    protected Workshop workshop1;
    protected Workshop workshop2;

    protected Vehicle(Workshop workshop1, Workshop workshop2) {
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    public abstract void manufacture();
}

class Car extends Vehicle {
    public Car(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    public void manufacture() {
        System.out.print("Car");
        workshop1.work();
        workshop2.work();
    }
}

interface Workshop {
    void work();
}

class Produce implements Workshop {
    @Override
    public void work() {
        System.out.print(" Produced");
    }
}

class Assemble implements Workshop {
    @Override
    public void work() {
        System.out.print(" Assembled");
    }
}