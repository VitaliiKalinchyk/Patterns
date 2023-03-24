package patterns;

import java.io.*;

public class MementoPattern {
    public static void main(String[] args) throws IOException {
        File file = new File("memento");
        file.createNewFile();
        file.deleteOnExit();

        Person person = new Person(45, "Katy");
        Memento.save(person, file);

        Person memento = Memento.restore(Person.class, file);
        System.out.println(memento);
    }
}


class Memento {
    public static <T extends Serializable> void save(T t, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Serializable> T restore(Class<T> tClass, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object o = objectInputStream.readObject();
            return tClass.cast(o);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class Person implements Serializable {
    private int id;
    private String name;

    public Person() {}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}