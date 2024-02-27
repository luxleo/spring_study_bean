package spring.prac.oop1.member;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.prac.oop1.config.AppConfig;

public class MemberMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);
        Member m1 = Member.builder()
                .id(1L)
                .name("m1")
                .grade(Grade.VIP)
                .build();

        memberService.join(m1);
        Member member = memberService.findMember(1L);

        System.out.println("member = " + member);
    }
}
