package v1.car.model;

public class CarResponse {

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
