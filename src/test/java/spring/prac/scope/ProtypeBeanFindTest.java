package spring.prac.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * 프로토타입 빈은 스프링 컨테이너 조회시 마다 새로 생성 및 초기화 된다.
 * 빈을 호출한 클라이언트가 관리해야 하므로 생성, 의존관계 주입 후는 클라이언트가 관리해야한다. -> 파괴자 호출 하지 않는다.
 */
public class ProtypeBeanFindTest {
    @Test
    void proxyBeanFindTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        System.out.println("find prototype bean 1");
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find prototype bean 1");
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);

        Assertions.assertThat(bean1).isNotSameAs(bean2);
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }
        @PreDestroy
        public void close() {
            System.out.println("ProtoTypeBean.close");
        }
    }
}
