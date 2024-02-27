package spring.prac.oop1.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.prac.oop1.member.Grade;
import spring.prac.oop1.member.Member;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount;

    public FixDiscountPolicy() {
        this(1000);
    }

    public FixDiscountPolicy(int discountFixAmount) {
        this.discountFixAmount = discountFixAmount;
    }

    @Override
    public int discount(Member member, int price) {
        int result = 0;
        
        if(member.getGrade() == Grade.VIP)
            result = discountFixAmount;

        return result;
    }
}
