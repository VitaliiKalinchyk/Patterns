package patterns;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {
    public static void main(String[] args) {
        Composite components = new Composite();
        components.add(new LeafA());
        components.add(new LeafA());
        components.add(new LeafB());
        Composite subComponents = new Composite();
        subComponents.add(new LeafA());
        subComponents.add(new LeafB());
        subComponents.add(new LeafB());
        components.add(subComponents);

        components.execute();
    }
}

interface Component {   //спільний інтерфейс для кокретних реалізацій і колекція цих реалізацій
    void execute();
}

class LeafA implements Component { //конкретна реалізація
    @Override
    public void execute() {
        System.out.println("This is Leaf A");
    }
}

class LeafB implements Component {
    @Override
    public void execute() {
        System.out.println("This is Leaf B");
    }
}

class Composite implements Component {  //колекція кокретних реалізацій
    private final List<Component> list = new ArrayList<>();

    void add(Component component) {
        list.add(component);
    }

    void remove(Component component) {
        list.remove(component);
    }

    Component getComponent(int index) {
        return list.get(index);
    }

    @Override
    public void execute() {
        list.forEach(Component::execute);
    }
}