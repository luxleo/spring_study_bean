package spring.prac.lifecycle.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 스프링 컨테이너 생성 -> 2. 빈 생성 -> 3. 의존 관계 주입 -> 초기화 콜백 -> 빈 사용
 * -> 소멸전 콜백 -> 7. 스프링 컨테이너 종료.
 */
public class LifeCycleTestV1 {
    @Test
    @DisplayName("빈 초기화 이후, 빈 종료 전 콜백 함수를 호출할 수 없다.")
    void lifeCycleTest() {
        // 빈 생성 및 의존 관계 주입 단계 -> 초기화 단계가 실행되지 않는다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV1.class);
        NetworkClientV1 client = ac.getBean(NetworkClientV1.class);

        // 빈 인스턴스 생성후 변수로 접근하여 빈의 초기화를 한다.
        client.call("[before] hi there");
        client.setUrl("http:hello-spring.net");
        client.call("[after]hi there");
        ac.close(); // 컨테이너를 종료한다. ConfigurableApplicationContext 만 가능하다.
    }

    @Configuration
    static class LifeCycleConfigV1 {

        /**
         * 스프링 컨테이너에서 빈 인스턴스를 생성하고 의존관계 주입만 담당한다.
         * 빈 메서드 내에서 초기화는 유효하지 않다.(빈 간의 의존관계가 아니므로)
         * @return
         */
        @Bean
        public NetworkClientV1 networkClientV1() {
            NetworkClientV1 networkClientV1 = new NetworkClientV1();
            networkClientV1.setUrl("http:hello-spring.net");
            System.out.println("networkClientV1.getUrl() = " + networkClientV1.getUrl());
            return networkClientV1;
        }
    }
}
