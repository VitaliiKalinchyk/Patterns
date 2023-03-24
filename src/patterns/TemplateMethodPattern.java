package patterns;

public class TemplateMethodPattern {
    public static void main(String[] args) {
        Walk kyivWalk = new KyivWalk();
        Walk lvivWalk = new LvivWalk();
        kyivWalk.walking();
        System.out.println("~~~");
        lvivWalk.walking();
    }
}

abstract class Walk {
    public void walking() {
        drinkCoffee();
        eatFood();
        watchCity();
        goingHome();
    }
    void drinkCoffee() {
        System.out.println("We need some coffee");
    }
    abstract void eatFood();
    abstract void watchCity();
    void goingHome() {
        System.out.println("Tired but happy");
    }
}
class KyivWalk extends Walk {
    @Override
    void eatFood() {
        System.out.println("breakfast at Honey");
    }
    @Override
    void watchCity() {
        System.out.println("Enjoying Podil view");
    }
}
class LvivWalk extends Walk {
    @Override
    void eatFood() {
        System.out.println("breakfast at Bachevski");
    }
    @Override
    void watchCity() {
        System.out.println("Enjoying City Center view");
    }
}