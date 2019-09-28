package v1.model.model;

import v1.CommonResponse;

public class CreateModelResponse extends CommonResponse {

    private Long id;

    public CreateModelResponse(Long id, String status) {
        this.id = id;
        setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
