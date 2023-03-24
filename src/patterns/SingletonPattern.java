package patterns;

public class SingletonPattern {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton instance = Singleton.getInstance("number - " + Thread.currentThread().getId());
                System.out.println(instance.getData());
            }).start();
        }
        Thread.sleep(100);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> System.out.println(NotLazySingleton1.singleton)).start();
        }
        Thread.sleep(100);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> System.out.println(NotLazySingleton2.SINGLETON)).start();
        }
        Thread.sleep(100);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton instance = LazySingleton.getInstance("number - " + Thread.currentThread().getId());
                System.out.println(instance.getData());
            }).start();
        }
        Thread.sleep(100);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(SingletonHolder.getInstance());
            }).start();
        }
    }
}

class Singleton {
    private String data;

    private static Singleton singleton;

    private Singleton(String data) {
        this.data = data;
    }

    public synchronized static Singleton getInstance(String data) {
            if (singleton == null) {
                singleton = new Singleton(data);
            }
            return singleton;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class NotLazySingleton1 {
    private final String data = "some data";
    public static final NotLazySingleton1 singleton = new NotLazySingleton1();

    private NotLazySingleton1() {}
}

enum NotLazySingleton2 {
    SINGLETON
}

class LazySingleton {
    private String data;

    private static volatile LazySingleton singleton;

    private LazySingleton(String data) {
        this.data = data;
    }

    public static LazySingleton getInstance(String data) {
        if (singleton == null) {
            synchronized (LazySingleton.class) {
                if (singleton == null) {
                    singleton = new LazySingleton(data);
                }
            }
        }
        return singleton;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class SingletonHolder {
    private static class Holder {
        static final SingletonHolder singleton = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        return Holder.singleton;
    }

    private SingletonHolder() {}
}