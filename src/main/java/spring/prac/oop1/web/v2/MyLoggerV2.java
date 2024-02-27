package spring.prac.oop1.web.v2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 프록시를 DI하여 스코프가 request라도 참조할 수 있도록하고
 * 참조하여 호출할 경우 실제로 생성한다.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // interface면 ScopedProxyMode.TARGET_INTERFACES
@Component
public class MyLoggerV2 {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        String msg = String.format("[%s][%s]%s", uuid, requestURL, message);
        System.out.println(msg);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        String msg = String.format("[%s] request scope bean created: %s", uuid, this.toString());

        System.out.println(msg);
    }

    @PreDestroy
    public void close() {
        String msg = String.format("[%s] request scope bean close: %s", uuid, this.toString());
        System.out.println(msg);
    }
}
