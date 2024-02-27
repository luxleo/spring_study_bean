package spring.prac.poly;

public class ClassB extends ClassA{
    public void callB() {
        System.out.println("ClassB.callB");
    }
    @Override
    public void call() {
        System.out.println("ClassB.call");
    }
}
