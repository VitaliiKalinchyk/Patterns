package patterns;

public class StatePattern {
    public static void main(String[] args) {
        ClassWithState object = new ClassWithState();
        for (int i = 0; i < 2; i++) {
            object.on();
        }
        for (int i = 0; i < 2; i++) {
            object.off();
        }
        for (int i = 0; i < 4; i++) {
            object.on();
            object.off();
        }
    }
}

class ClassWithState {
    State state = new StateOff(this);
    int numberOfAttempts = 5;

    public void setState(State state) {
        this.state = state;
        numberOfAttempts--;
    }

    void on() {
        state.on();
    }
    void off() {
        state.off();
    }
}

abstract class State {
    ClassWithState classWithState;

    public State(ClassWithState classWithState) {
        this.classWithState = classWithState;
    }

    abstract void on();
    abstract void off();
}

class StateOn extends State {

    public StateOn(ClassWithState classWithState) {
        super(classWithState);
    }

    @Override
    public void on() {
        System.out.println("Already working");
    }

    @Override
    public void off() {
        System.out.println("Shutting down");
        if (classWithState.numberOfAttempts != 0) {
            classWithState.setState(new StateOff(classWithState));
        } else {
            classWithState.setState(new StateBroken(classWithState));
        }
    }
}

class StateOff extends State {

    public StateOff (ClassWithState classWithState) {
        super(classWithState);
    }

    @Override
    public void on() {
        System.out.println("Now it's working");
        if (classWithState.numberOfAttempts != 0) {
            classWithState.setState(new StateOn(classWithState));
        }else {
            classWithState.setState(new StateBroken(classWithState));
        }
    }

    @Override
    public void off() {
        System.out.println("I's already off");
    }
}

class StateBroken extends State {

    public StateBroken (ClassWithState classWithState) {
        super(classWithState);
    }

    @Override
    public void on() {
        System.out.println("It's broken!");
    }

    @Override
    public void off() {
        System.out.println("It's broken!");
    }
}