package v1.car.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonCarRequest {

    @JsonProperty("model_id")
    protected Long modelId;

    @JsonProperty("year_prod")
    protected Integer yearProd;

    protected String cost;

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
}
