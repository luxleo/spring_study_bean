package spring.prac.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.prac.oop1.discount.DiscountPolicy;
import spring.prac.oop1.discount.FixDiscountPolicy;
import spring.prac.oop1.discount.RateDiscountPolicy;
import spring.prac.oop1.member.MemberRepository;
import spring.prac.oop1.member.impl.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppllicationBeanSameBeanTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입의 빈이 여러개 있다면 에러")
    void findByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(MemberRepository.class);
        });
    }

    @Test
    @DisplayName("같은 타입의 빈이 여러개일때 빈 이름을 지정하여 조회가능하다.")
    void findByName() {
        MemberRepository repository = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(repository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("해당 타입의 빈 모두 조회")
    void findBeansByType() {

        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (Map.Entry<String, MemberRepository> entry :
                beansOfType.entrySet()) {
            String info = String.format("key : %s, value : %s", entry.getKey(), entry.getValue().toString());
            System.out.println("info = " + info);
        }
    }

    @Test
    @DisplayName("빈 조회시 상속 관계를 이용하여 조회 할 수 있다.")
    void findByExtends() {
        Map<String, MemberRepository> type1 = ac.getBeansOfType(MemberRepository.class);
        assertThat(type1.size()).isEqualTo(2);

        Map<String, DiscountPolicy> type2 = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(type2.size()).isEqualTo(2);

        String[] definitionNames = ac.getBeanDefinitionNames();

        for (String definitionName :
                definitionNames) {
            BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) ac).getBeanDefinition(definitionName);

            // 내가 직접 정의한 빈일 경우 출력해보기
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("definitionName = " + definitionName);
            }
        }

    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

        @Bean
        public DiscountPolicy discountPolicy1() {
            return new FixDiscountPolicy();
        }
        @Bean
        public DiscountPolicy discountPolicy2() {
            return new RateDiscountPolicy();
        }
    }
}
