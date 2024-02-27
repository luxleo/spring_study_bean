package spring.prac.oop1.web.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoServiceV2 {
    private final MyLoggerV2 logger;
    public void logic(String serviceId) {
        logger.log(String.format("service id = %s",serviceId));
    }
}
