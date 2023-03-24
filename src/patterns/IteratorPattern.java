package patterns;

import java.util.Iterator;
import java.util.Random;

public class IteratorPattern {
    public static void main(String[] args) {
        ClassToIterate iterableClass = new ClassToIterate();
        Iterator<ClassToIterate.SomeProduct> iterator = iterableClass.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class ClassToIterate {
    SomeProduct[] array = {new SomeProduct(), new SomeProduct(), new SomeProduct()};

    Iterator<SomeProduct> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < array.length;
            }
            @Override
            public SomeProduct next() {
                return array[cursor++];
            }
        };
    }
    static class SomeProduct {
        @Override
        public String toString() {
            return "SomeProduct{}" + new Random().nextInt(100);
        }
    }
}
