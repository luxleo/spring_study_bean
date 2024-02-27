package spring.prac.oop1.web.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoServiceV1 {
    // private final MyLoggerV1 logger; @Scope("request")는 사용자의 요청이 올때 생성되므로 싱글톤빈인 컨트롤러애 주입될 수 없다.
    private final ObjectProvider<MyLoggerV1> provider;
    public void logic(String serviceId) {
        MyLoggerV1 logger = provider.getObject();
        logger.log(String.format("service id = %s",serviceId));
    }
}
