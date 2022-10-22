package antisolid;


public class AntiI implements interfaceAnti {
    @Override
    public void doSay() {
        try {
            System.out.println("hello");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    @Override
    public void doPlay() {

    }

    @Override
    public void doSing() {

    }

    @Override
    public void doSleep() {

    }
}
//Нарушение I-принципа
interface interfaceAnti{
    void doSay();
    void doPlay();
    void doSing();
    void doSleep();
}