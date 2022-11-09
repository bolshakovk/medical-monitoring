package liga.medical.medicalmonitoring.core.antisolid;
//Пример, человек, у которого есть обязанности петь и танцевать назван классом AntiS
//По хорошему нужно разделить два метода по разным классам
public class AntiS {
    //нарушена инкапсуляция
    public int age;
    public String name;
    //Ненужная ссылка на объект класса
    public AntiS friendHuman;

    public AntiS(int age, String name) {
        this.age = age;
        this.name = name;
    }

    //Следует разделить эти методы по двум новым классам: Singer, Dancer
    public void sing(){
        System.out.println("I'm singing, i'm " + age+ " y.o, my name is " + name);
    }
    public void dance(){
        //ненужная валидация
        if (age < 50){
            System.out.println("I'm dancing, i'm " + age+ " y.o, my name is " + name);
        }else {
            System.out.println("I'm too old for dancing");
        }
    }
}
