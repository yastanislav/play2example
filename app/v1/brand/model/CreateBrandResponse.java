package v1.brand.model;

import v1.CommonResponse;

public class CreateBrandResponse extends CommonResponse {

    private Long id;

    public CreateBrandResponse(Long id, String status) {
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
