package spring.prac.poly;

public class PolyMain {
    public static void main(String[] args) {
        ClassA poly = new ClassB();
        ClassB classB = new ClassB();

        // 다형적 참조로 서브타입 인스턴스를 수퍼타입으로 참조시 서브타입 인스턴스의 메소드는 참조할 수 없다.
        //poly.callB();
        poly.call();

        classB.call();
        classB.callB();
    }
}
