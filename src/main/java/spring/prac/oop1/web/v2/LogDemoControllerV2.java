package spring.prac.oop1.web.v2;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoControllerV2 {
    private final MyLoggerV2 logger;
    private final LogDemoServiceV2 logService;

    @GetMapping("log-demo/v2")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        logger.setRequestURL(requestURL);

        logger.log("controller test");
        logService.logic("testId");
        return "OK";
    }
}
