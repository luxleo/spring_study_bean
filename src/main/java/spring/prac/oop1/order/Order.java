package spring.prac.oop1.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private Long memberId;
    private String itmeName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itmeName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itmeName = itmeName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

    @Override
    public String toString() {
        return String.format("[Order] member id = %d / item name = %s / final price = %d",
                memberId, itmeName, calculatePrice());
    }
}
