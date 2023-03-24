package patterns;

import java.util.*;

public class AdapterPattern {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, i * 10);
        }
        Adapter adapter = new Adapter(map);
        System.out.println(map);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(i);
        }
        IteratorToEnumeration<Integer> enumeration = new IteratorToEnumeration<>(list.iterator());
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }


    }
}

class Adapter extends AbstractSet<Integer> {

    private Map<Integer, Integer> map;

    public Adapter(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public boolean add(Integer integer) {
        map.put(integer, 0);
        return true;
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }
}

class IteratorToEnumeration<T> implements Enumeration<T> {
    private Iterator<T> iterator;

    public IteratorToEnumeration(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return iterator.next();
    }
}

