package spring.prac.lifecycle.v3;

public class NetworkClientV3 {
    private String url;

    public NetworkClientV3() {
        System.out.println("생성자 호출");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void call(String message) {
        System.out.println("call : "+ url+ "\tmessage: "+message);
    }

    public void connect() {
        System.out.println("connect\turl: " + url);
    }

    public void disconnect() {
        System.out.println("disconnect\turl: " + url);
    }

    // 빈에 수동 등록할 초기화 메서드
    public void init() {
        connect();
        call("초기화 연결 메시지");
    }

    // 빈에 수동 등록할 종료전 메서드
    public void destroy() {
        disconnect();
    }
}
