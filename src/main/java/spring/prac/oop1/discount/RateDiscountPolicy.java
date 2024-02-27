package spring.prac.oop1.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.prac.oop1.annotation.MainDiscountPolicy;
import spring.prac.oop1.member.Grade;
import spring.prac.oop1.member.Member;

@Component
@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
    private final int discountRate;

    public RateDiscountPolicy() {
        this(10);
    }

    public RateDiscountPolicy(int discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public int discount(Member member, int price) {
        int discountPrice = 0;
        if(member.getGrade() == Grade.VIP)
            discountPrice = (price * 10) / 100;
        return discountPrice;
    }
}
