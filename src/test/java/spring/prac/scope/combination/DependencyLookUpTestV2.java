package spring.prac.scope.combination;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * singleton 스코프의 빈 안의 필드에 프로토타입이 있는 경우
 * 호출마다 새롭게 생성되지 않는 문제를 해결하기 위하여 이미 스프링 컨테이너에 등록된 프로토타입빈을 조회하는 것을
 * Dependency Look up이라고 한다.
 *
 * 스프링이 지원하는 DL:
 *      ObjectFactory, ObjectProvider(ObejctFactory의 기능을 확장함)
 * 자바가 지원하는 DL:
 *      Provider(추가 의존 설정 필요), @LookUp 어노테이션 이용
 */
public class DependencyLookUpTestV2 {

    @Test
    void dependencyLookUp() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        assertThat(clientBean1.logic()).isEqualTo(1);
        assertThat(clientBean2.logic()).isEqualTo(1);
    }
    @Scope("singleton")
    static class ClientBean {
        private final ObjectProvider<ProtoTypeBean> provider;

        ClientBean(ObjectProvider<ProtoTypeBean> provider) {
            this.provider = provider;
        }

        public int logic() {
            ProtoTypeBean protoTypeBean = provider.getObject();
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        private int count;

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }

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
