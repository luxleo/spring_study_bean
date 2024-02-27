package spring.prac.oop1.config;

import spring.prac.oop1.discount.DiscountPolicy;
import spring.prac.oop1.discount.FixDiscountPolicy;
import spring.prac.oop1.member.MemberRepository;
import spring.prac.oop1.member.MemberService;
import spring.prac.oop1.member.impl.MemberServiceImpl;
import spring.prac.oop1.member.impl.MemoryMemberRepository;
import spring.prac.oop1.order.OrderService;
import spring.prac.oop1.order.OrderServiceImpl;

public class AppConfigPlain {

    // 멤버 구성 영역
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 주문 구성 영역
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
