package v1.car.entity;

import v1.car.model.CommonCarRequest;

public class Car {

    private Long id;
    private Long modelId;
    private Integer yearProd;
    private String cost;

    public Car() {
    }

    public Car(CommonCarRequest commonCarRequest) {
        this.modelId = commonCarRequest.getModelId();
        this.yearProd = commonCarRequest.getYearProd();
        this.cost = commonCarRequest.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", modelId=" + modelId +
                ", yearProd=" + yearProd +
                ", cost='" + cost + '\'' +
                '}';
    }
}
