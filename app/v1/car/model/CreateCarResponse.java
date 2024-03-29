package v1.car.model;

import v1.CommonResponse;

public class CreateCarResponse extends CommonResponse {

    private Long id;

    public CreateCarResponse(Long id, String status) {
        this.id = id;
        this.setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
