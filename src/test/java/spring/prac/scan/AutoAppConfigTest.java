package spring.prac.scan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.prac.oop1.config.AutoAppConfigV1;
import spring.prac.oop1.config.AutoAppConfigV2;
import spring.prac.oop1.discount.DiscountPolicy;
import spring.prac.oop1.member.Grade;
import spring.prac.oop1.member.Member;
import spring.prac.oop1.member.MemberService;
import spring.prac.oop1.member.impl.MemberServiceImpl;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfigV1.class);
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("의존관계 자동 주입시 유일한 타입의 빈이 아니면 에러 발생한다.")
    void noUnique() {
        /* @Primary, @Qualifier로 지정해주기전에는 유효했다. 지금은 아님
        org.junit.jupiter.api.Assertions.assertThrows(UnsatisfiedDependencyException.class, () -> {
            new AnnotationConfigApplicationContext(AutoAppConfigV2.class);
        });
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfigV2.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = Member.builder()
                .id(1L)
                .name("member")
                .grade(Grade.VIP)
                .build();

        int discountPrice = discountService.discount(member, 10000, "rateDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        discountService.showAllPolicies();
    }

    /**
     * 설정 파일에 빈으로 등록하여 읽으면
     * 자동으로 DiscountPolicy들을 등록해준다.
     */
    static class DiscountService{
        /**
         * 맵의 키에 DiscountPolicy 타입으로 조회한 모든 스프링 빈의 이름을 키르 등록
         * 빈을 값으로 등록한다.
         */
        private final Map<String, DiscountPolicy> policyMap;

        /**
         * 리스트안에 DiscountPolicy 타입의 빈들을 넣어준다.
         */
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> map, List<DiscountPolicy> policies) {
            this.policyMap = map;
            this.policies = policies;
        }

        public int discount(Member member, int price, String policyName) {
            DiscountPolicy discountPolicy = policyMap.get(policyName);
            return discountPolicy.discount(member, price);
        }

        public void showAllPolicies() {
            policies.stream()
                    .forEach(el -> {
                        System.out.println("el = " + el);
                    });
        }
    }
}
