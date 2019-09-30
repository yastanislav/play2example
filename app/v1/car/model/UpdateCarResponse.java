package v1.car.model;

import v1.CommonResponse;

public class UpdateCarResponse extends CommonResponse {

    public UpdateCarResponse(String status) {
        setStatus(status);
    }
}
