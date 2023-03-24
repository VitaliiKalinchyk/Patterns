package patterns;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ProxyPattern {
    public static void main(String[] args) throws Exception {
        My my = new MyProxy();
        System.out.println(my.getField());
        System.out.println("~~~");
        System.out.println(my.getField());
        System.out.println("~~~");
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(my.getField());
    }
}

class My {
    private Field field = new Field();
    public Field getField() {
        System.out.println("MyField");
        return field;
    }
}

class MyProxy extends My {
    private WeakReference<Field> field;
    public Field getField() {
        if (field == null || field.get() == null) {
            System.out.println("MyProxyField");
            field = new WeakReference<>(new My().getField());
        }
        return field.get();
    }
}
class Field {
    String s = "field";

    @Override
    public String toString() {
        return "Field{" +
                "s='" + s + '\'' +
                '}';
    }
}