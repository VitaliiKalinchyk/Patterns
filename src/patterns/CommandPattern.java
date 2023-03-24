package patterns;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandPattern {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        PrintGreetings greet = new PrintGreetings();
        invoker.setCommand(greet::printHello);
        invoker.executeCommand();
        invoker.setCommand(greet::printGoodDay);
        invoker.executeCommand();
        PrintByes bye = new PrintByes();
        invoker.setCommand(bye::printBye);
        invoker.executeCommand();
        invoker.setCommand(new MacroCommand());
        invoker.executeCommand();
        Deque<Command> commands = new ArrayDeque<>();
        commands.add(greet::printHello);
        commands.add(greet::printGoodDay);
        commands.add(bye::printBye);
        commands.forEach(Command::execute);
    }
}

class Invoker {
    private Command command;
    void setCommand(Command command) {
        this.command = command;
    }
    void executeCommand() {
        command.execute();
    }
}

@FunctionalInterface
interface Command {
    void execute();
}

class HelloCommand implements Command {
    private final PrintGreetings print;

    public HelloCommand(PrintGreetings print) {
        this.print = print;
    }

    @Override
    public void execute() {
        print.printHello();
    }
}

class GoodDayCommand implements Command {
    private final PrintGreetings print;

    public GoodDayCommand(PrintGreetings print) {
        this.print = print;
    }

    @Override
    public void execute() {
        print.printGoodDay();
    }
}

class ByeCommand implements Command {
    private final PrintByes print;

    public ByeCommand(PrintByes print) {
        this.print = print;
    }
    @Override
    public void execute() {
        print.printBye();
    }
}

class MacroCommand implements Command {
    Command[] commands = {new HelloCommand(new PrintGreetings()),
            new GoodDayCommand(new PrintGreetings()),
            new ByeCommand(new PrintByes())};

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}

class PrintGreetings {
    void printHello() {
        System.out.println("Hello");
    }
    void printGoodDay() {
        System.out.println("Good day");
    }
}

class PrintByes {
    void printBye() {
        System.out.println("Bye");
    }
}