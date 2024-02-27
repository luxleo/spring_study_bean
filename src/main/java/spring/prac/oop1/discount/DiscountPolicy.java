package spring.prac.oop1.discount;

import spring.prac.oop1.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
