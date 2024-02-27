package spring.prac.beanfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.prac.oop1.config.AppConfig;
import spring.prac.oop1.member.MemberService;
import spring.prac.oop1.member.impl.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApplicationBasicContextTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름을 통하여 조회, 빈 이름과 타입을 지정하여 조회")
    void findBeanByName() {
        // 타입 지정하지 않고 이름 만으로 조회시 Object class
        Object memberService = ac.getBean("memberService");
        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        assertThat(memberService1).isEqualTo(memberService);
    }

    @Test
    @DisplayName("타입 만으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanBySubClass() {
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);

        // 클래스 상속시 메모리 구조로 인하여 슈퍼타입 인스턴스도 함께 조회가능하다.
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름으로 조회 시 없는 경우 NoSuchBeanDefinition 에러를 반환한다.")
    void noSuchBean() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("xxxx", MemberService.class);
        });
    }
}
