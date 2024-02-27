package spring.prac.oop1.order;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.prac.oop1.config.AppConfig;
import spring.prac.oop1.member.Grade;
import spring.prac.oop1.member.Member;
import spring.prac.oop1.member.MemberService;

public class OrderMain {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);

        long memberId = 1L;
        Member m1 = Member.builder()
                .id(memberId)
                .name("m1")
                .grade(Grade.VIP)
                .build();

        memberService.join(m1);
        Member findMember = memberService.findMember(memberId);

        Order order1 = orderService.createOrder(memberId, "item1", 10000);
        int finalPrice = order1.calculatePrice();
        System.out.println("finalPrice = " + finalPrice);
    }
}
