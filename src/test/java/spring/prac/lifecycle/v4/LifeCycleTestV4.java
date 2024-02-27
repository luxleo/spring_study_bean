package spring.prac.lifecycle.v4;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 빈에 수동으로 초기화, 종료전 메서드를 등록할 수 있다.
 *
 * 장점:
 *      1. 자바 어노테이션이다.
 *      2. 컴포넌트 스캔과 잘 어울린다.(@Bean 에 수동등록 하는게 아니므로)
 *      3. 스프링 컨테이너 뿐만 아니라 다른 컨테이너 위에서도 동작한다.
 * 단점:
 *      외부라이브러리에는 사용할 수 없다.
 *      이 경우에 @Bean(init, destroyMethods)에 등록하여 사용하자.
 */
public class LifeCycleTestV4 {
    @Test
    void lifeSpanTest() {
         ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigV4.class);
        NetworkClientV4 client = ac.getBean(NetworkClientV4.class);

        ac.close();
    }
    @Configuration
    static class LifeCycleConfigV4 {
        @Bean
        public NetworkClientV4 networkClientV3() {
            NetworkClientV4 networkClientV4 = new NetworkClientV4();
            networkClientV4.setUrl("http:good-init");
            return networkClientV4;
        }
    }
}
