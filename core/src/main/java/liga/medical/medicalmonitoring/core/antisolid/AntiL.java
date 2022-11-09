package liga.medical.medicalmonitoring.core.antisolid;


public class AntiL {
    Shape shape;
    public void func(){
        shape.drawShape(shape);
    }
}
interface Shape{
    void drawShape(Shape shape);
}

class Square implements Shape{

    //Нарушение S, O-принципа
    public void drawSquare(Shape shape){
        System.out.println("drawSquare");
    }
    public void drawCircle(Shape shape){
        System.out.println("drawCircle");
    }

    //Нарушение L-принципа
    public void drawShape(Shape shape){
        if (shape instanceof Square) {
            drawSquare((Parralelogramm) shape);
        } else {
            drawCircle((Oval) shape);
        }
    }
}
//Нарушение S, O-принципа
class Circle implements Shape{

    //Нарушение S, O-принципа
    public void drawSquare(Shape shape){
        System.out.println("drawSquare");
    }
    public void drawCircle(Shape shape){
        System.out.println("drawCircle");
    }

    //Нарушение L-принципа
    public void drawShape(Shape shape){
        if (shape instanceof Square) {
            drawSquare((Parralelogramm) shape);
        } else {
            drawCircle((Oval) shape);
        }
    }
}
class Oval extends Circle{
    public void drawCircle(Shape shape){
        System.out.println("null");
    }
}
class Parralelogramm extends Square{
    public void drawSquare(Shape shape){
        System.out.println("null");
    }
}
