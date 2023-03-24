package patterns;

import java.util.ArrayList;
import java.util.List;

public class BuilderPattern {
    public static void main(String[] args) {
        ListBuilder<Integer> builder = new ListBuilder<>();
        builder.add(1).add(2).add(3);
        List<Integer> list = builder.getList();
        System.out.println(list);

        SimpleEntity joe = SimpleEntity.builder().id(99).name("Joe").build();
        System.out.println(joe);
    }
}

class ListBuilder <T> {
    private final List<T> list;

    public ListBuilder() {
        this.list = new ArrayList<>();
    }

    ListBuilder<T> add(T t) {
        list.add(t);
        return this;
    }

    List<T> getList() {
        return list;
    }
}

class SimpleEntity {
    private int id;
    private String name;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SimpleEntity entity = new SimpleEntity();

        public Builder id(int id) {
            entity.id = id;
            return this;
        }

        public Builder name(String name) {
            entity.name = name;
            return this;
        }

        public SimpleEntity build() {
            return entity;
        }
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}