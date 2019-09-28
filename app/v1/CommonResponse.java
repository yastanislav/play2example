package v1;

public class CommonResponse {

    public static final String FAIL="FAIL";
    public static final String SUCCESS="SUCCESS";

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
