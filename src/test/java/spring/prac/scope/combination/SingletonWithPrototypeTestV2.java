package spring.prac.scope.combination;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로토타입빈은 매 요청마다 새로 생성되지만
 * 싱글톤빈의 필드로 의존관계 주입 된 경우
 * 싱글톤빈의 특성상 스프링 컨테이너의 생명주기와 같다.
 */
public class SingletonWithPrototypeTestV2 {
    @Test
    @DisplayName("싱글톤 빈과 함께 호출된 경우 프로토타입은 새로 생성되지 않는다.")
    void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        ClientBean bean2 = ac.getBean(ClientBean.class);

        assertThat(bean1.getPrototypeBean()).isSameAs(bean2.getPrototypeBean());

        bean1.logic();
        assertThat(bean1.getCount()).isEqualTo(1);

        bean2.logic();
        assertThat(bean2.getCount()).isEqualTo(2);

        ac.close();
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Scope("singleton") // 생략가능
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        @Autowired // ? 에러가 안나는데 -> intellij는 컴파일 에러를 잡는다.
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public void logic() {
            System.out.println("SingletonBean.logic called");
            prototypeBean.addCount();
        }

        public int getCount() {
            return prototypeBean.getCount();
        }

        public PrototypeBean getPrototypeBean() {
            return prototypeBean;
        }
    }
}
