package v1.car.entity;

import v1.car.model.CreateCarRequest;

public class Car {

    private Long id;
    private Long brandId;
    private Long modelId;
    private Integer yearProd;
    private String cost;

    public Car(CreateCarRequest createCarRequest) {
        this.brandId = createCarRequest.getBrandId();
        this.modelId = createCarRequest.getModelId();
        this.yearProd = createCarRequest.getYearProd();
        this.cost = createCarRequest.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Integer getYearProd() {
        return yearProd;
    }

    public void setYearProd(Integer yearProd) {
        this.yearProd = yearProd;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", modelId=" + modelId +
                ", yearProd=" + yearProd +
                ", cost='" + cost + '\'' +
                '}';
    }
}
