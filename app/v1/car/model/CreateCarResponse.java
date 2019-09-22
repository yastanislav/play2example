package v1.car.model;

public class CreateCarResponse extends CarResponse {

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
