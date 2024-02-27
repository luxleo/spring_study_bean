package spring.prac.oop1.order;

public interface OrderService {
    public abstract Order createOrder(long memberId, String itemName, int itemPrice);
}
