package spring.prac.lifecycle.v3;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 빈에 수동으로 초기화, 종료전 메서드를 등록할 수 있다.
 *
 * 장점:
 *      1. 메서드 이름을 자유롭게 줄 수 있다.
 *      2. 외부 라이브러리에도 수동 등록하여 사용할 수 있다.
 *      3. 스프링 빈이 스프링 코드에 의존하지 않는다.
 * 팁:
 *      destroyMethods 는 기본적으로 (inferred)가 할당되어 있는데
 *      이때 close, shutdown 의 이름을 지정해두면 등록하지 않아도 된다.
 */
public class LifeCycleTestV3 {
    @Test
    void lifeSpanTest() {
         ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV3.class);
        NetworkClientV3 client = ac.getBean(NetworkClientV3.class);

        ac.close();
    }
    @Configuration
    static class LifeCycleConfigV3 {
        @Bean(initMethod = "init", destroyMethod = "destroy")
        public NetworkClientV3 networkClientV3() {
            NetworkClientV3 networkClientV3 = new NetworkClientV3();
            networkClientV3.setUrl("http:good-init");
            return networkClientV3;
        }
    }
}
