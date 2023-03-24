package patterns;

public class FacadePattern {
    public static void main(String[] args) {
        ChorusFacade chorusFacade = new ChorusFacade();
        chorusFacade.singingAll();
    }
}

class ChorusFacade {
    private final MenChorus menChorus = new MenChorus();
    private final WomenChorus womenChorus = new WomenChorus();

    void singingAll() {
        menChorus.singing();
        womenChorus.singing();
    }
}

class MenChorus {
    void singing(){
        System.out.println("men are singing");
    }
}
class WomenChorus {
    void singing(){
        System.out.println("women are singing");
    }
}
