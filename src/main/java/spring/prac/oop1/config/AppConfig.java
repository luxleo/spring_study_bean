package spring.prac.oop1.config;

import org.springframework.context.annotation.Bean;
import spring.prac.oop1.discount.DiscountPolicy;
import spring.prac.oop1.discount.FixDiscountPolicy;
import spring.prac.oop1.member.MemberRepository;
import spring.prac.oop1.member.MemberService;
import spring.prac.oop1.member.impl.MemberServiceImpl;
import spring.prac.oop1.member.impl.MemoryMemberRepository;
import spring.prac.oop1.order.OrderService;
import spring.prac.oop1.order.OrderServiceImpl;

//@Configuration -> @Scope("request") 테스트를 위하여 잠시 비활성화
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 주문 영역

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
