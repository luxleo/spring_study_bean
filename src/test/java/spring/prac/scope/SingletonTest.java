package spring.prac.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 싱글톤 빈은 스프링 컨테이너에서 생성, 의존관계 주입을 포함하여 전체 생애를 관리한다.
 * 모든 호출에 같은 인스턴스를 반환한다.
 */
public class SingletonTest {
    @Test
    void singletonScopeTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        assertThat(bean1).isSameAs(bean2);
        ac.close();
    }
    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        void init() {
            System.out.println("SingletonBean.init");
        }

        // @Bean(initMethod, destroyMethod) 에 등록시 close, shutdown은 자동으로 등록한다.
        @PreDestroy
        void close() {
            System.out.println("SingletonBean.close");
        }
    }
}
