package spring.prac.lifecycle.v2;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InitializingBean(초기화 콜백), DisposableBean(소멸전 콜백)으로
 * 빈 라이프 사이클을 관리할 수 있다.
 *
 * 단점:
 *      1. 스프링코드에 의존적이다.
 *      2. 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 */
public class LifeCycleTestV2 {
    @Test
    void lifeCycleTest() {
        // 빈 생성 및 의존 관계 주입 단계 -> 초기화 단계가 실행되지 않는다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV2.class);
        NetworkClientV2 client = ac.getBean(NetworkClientV2.class);

        // 빈 인스턴스 생성후 변수로 접근하여 빈의 초기화를 한다.
        client.call("[before] hi there");
        client.setUrl("http:hello-spring.net");
        client.call("[after]hi there");
        ac.close(); // 컨테이너를 종료한다. ConfigurableApplicationContext 만 가능하다.
    }

    @Configuration
    static class LifeCycleConfigV2 {

        /**
         * DisposableBean, InitializingBean 을 통하여
         * 생성, 의존관계가 주입되고 난 후
         * 초기화 콜백을 먼저 호출하고 사용할 수 있도록 반환한다.
         *
         * 또한 컨테이너가 종료되기전 빈의 소멸전 콜백을 호출해주고 종료한다.
         */
        @Bean
        public NetworkClientV2 networkClientV1() {
            NetworkClientV2 networkClientV2 = new NetworkClientV2();
            networkClientV2.setUrl("http:hello-spring.net");
            return networkClientV2;
        }
    }
}
