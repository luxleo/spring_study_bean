package spring.prac.oop1.web.v1;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Scope("request")는 사용자의 요청이 오기전까지 생성이 되지 않는다.
 * 따라서 DI를 할 수가 없다.
 *
 * ObjectProvider를 이용하여 DL(Dependency Look up)을 이용하여 해결한다.
 */
@Controller
@RequiredArgsConstructor
public class LogDemoControllerV1 {
    // private final MyLoggerV1 logger; @Scope("request")는 사용자의 요청이 올때 생성되므로 싱글톤빈인 컨트롤러애 주입될 수 없다.
    private final LogDemoServiceV1 logService;
    private final ObjectProvider<MyLoggerV1> provider;

    @GetMapping("log-demo/v1")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        MyLoggerV1 logger = provider.getObject();
        String requestURL = request.getRequestURL().toString();
        logger.setRequestURL(requestURL);

        logger.log("controller test");
        logService.logic("testId");
        return "OK";
    }
}
