package patterns;

import java.util.*;

public class DecoratorPattern {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ListDecorator listDecorator = new ConcreteDecorator(list);
        System.out.println(listDecorator.produce());
        System.out.println(listDecorator.sum());
    }
}

abstract class ListDecorator extends AbstractList<Integer> {
    abstract int sum();
    abstract int produce();
}

class ConcreteDecorator extends ListDecorator {

    private final List<Integer> list;

    public ConcreteDecorator(List<Integer> list) {
        this.list = list;
    }

    @Override
    int sum() {
        return list.stream().mapToInt(Integer::valueOf).sum();
    }

    @Override
    int produce() {
        return list.stream().mapToInt(Integer::valueOf).reduce(1, Math::multiplyExact);
    }

    @Override
    public Integer get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
