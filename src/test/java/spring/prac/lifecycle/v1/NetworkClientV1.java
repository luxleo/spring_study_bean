package spring.prac.lifecycle.v1;

public class NetworkClientV1 {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public NetworkClientV1() {
        System.out.println("생성자 호출");
        connect();
        call("초기화 연결 메시지");
    }

    public String getUrl() {
        return url;
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
}
