package spring.prac.oop1.web.v1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Scope("request")
@Component
public class MyLoggerV1 {
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
