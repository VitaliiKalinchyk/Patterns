package patterns;

public class FlyweightPattern {
    public static void main(String[] args) {
        System.out.println(Flyweight.valueOf(1));
        System.out.println(Flyweight.valueOf(3));
    }
}

class Flyweight {
    private int id;
    private String name;

    private static Flyweight id1 = new Flyweight(1, "Joe");
    private static Flyweight id2 = new Flyweight(2, "Dave");
    private static Flyweight id3 = new Flyweight(3, "Chris");

    private Flyweight(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Flyweight valueOf(int id) {
        return switch (id) {
            case 1 -> id1;
            case 2 -> id2;
            case 3 -> id3;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Flyweight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}