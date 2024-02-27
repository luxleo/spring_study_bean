package spring.prac.scope.combination;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * 프로토타입 빈만 조회하여 로직을 실행하면
 * 각 조회를 요청하는 클라이언트 마다 새로운 프로토타입 빈 인스턴스를 할당 받으므로
 */
public class SingletonWithPrototypeTestV1 {
    @Test
    @DisplayName("프로토타입 빈만 호출 되었을 경우")
    void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void shutdown() {
            System.out.println("PrototypeBean.shutdown");
        }
    }
}
