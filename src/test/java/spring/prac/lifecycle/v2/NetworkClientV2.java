package spring.prac.lifecycle.v2;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClientV2 implements DisposableBean, InitializingBean {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public NetworkClientV2() {
        System.out.println("생성자 호출");
    }

    public void call(String message) {
        System.out.println("call: "+url+" message: "+message);
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    // 초기화 메서드
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }
}
