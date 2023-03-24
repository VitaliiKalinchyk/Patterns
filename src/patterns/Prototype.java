package patterns;

import java.io.*;
import java.util.Objects;

public class Prototype {
    public static void main(String[] args) {
        Entity entity = new Entity(3, "some@email.com");

        Entity clone = entity.clone();
        System.out.println(entity == clone);
        System.out.println(entity.equals(clone));

        Entity cloneConstructor = new Entity(entity);
        System.out.println(entity == cloneConstructor);
        System.out.println(entity.equals(cloneConstructor));

        Entity copy = entity.copy();
        System.out.println(entity == copy);
        System.out.println(entity.equals(copy));

        Entity serialize = entity.serialize();
        System.out.println(entity == serialize);
        System.out.println(entity.equals(serialize));


    }
}

class Entity implements Cloneable, Serializable {
    private int id;
    private String email;

    public Entity() {}
    public Entity(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Entity(Entity entity) {
        id = entity.id;
        email = entity.email;
    }

    public Entity copy() {
        return new Entity(this.id, this.email);
    }

    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Entity serialize() {
        Entity entity = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(this);
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
                entity = (Entity) objectInputStream.readObject();
            }
        } catch (Exception ignored) {}
        return entity;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", email='" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id && Objects.equals(email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}