package spring.prac.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.prac.oop1.discount.DiscountPolicy;
import spring.prac.oop1.discount.RateDiscountPolicy;
import spring.prac.oop1.member.MemberRepository;
import spring.prac.oop1.member.MemberService;
import spring.prac.oop1.member.impl.MemberServiceImpl;
import spring.prac.oop1.member.impl.MemoryMemberRepository;
import spring.prac.oop1.order.OrderService;
import spring.prac.oop1.order.OrderServiceImpl;

public class SingletonBeanTest {
    ApplicationContext ac1 = new AnnotationConfigApplicationContext(WithConfigurationCongfig.class);
    ApplicationContext ac2 = new AnnotationConfigApplicationContext(ConfigurationConfig.class);


    @Test
    @DisplayName("@Configuration 없이 하면 CGLIB에서 관리하지 않아 싱글톤 아님")
    void notSingleton() {
        // member Repository가 총 세번 호출된다.
        ac1.getBean(MemberRepository.class);
    }

    @Test
    @DisplayName("@Configuration은 기본적으로 빈을 싱글톤으로 관리한다.")
    void singleton() {
        ac2.getBean(MemberRepository.class);
    }
    static class WithoutConfigurationCongfig {
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("WithoutConfigurationCongfig.memberRepository");
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberService memberService() {
            System.out.println("WithoutConfigurationCongfig.memberService");
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrderService orderService() {
            System.out.println("WithoutConfigurationCongfig.orderService");
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }
    static class WithConfigurationCongfig{
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("WithConfigurationCongfig.memberRepository");
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberService memberService() {
            System.out.println("WithoutConfigurationCongfig.memberService");
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrderService orderService() {
            System.out.println("WithoutConfigurationCongfig.orderService");
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    @Configuration
    static class ConfigurationConfig{
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("ConfigurationConfig.memberRepository");
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberService memberService() {
            System.out.println("ConfigurationConfig.memberService");
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrderService orderService() {
            System.out.println("ConfigurationConfig.orderService");
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }
}
