package v1.car.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCarRequest {

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("model_id")
    private Long modelId;

    @JsonProperty("year_prod")
    private Integer yearProd;
    private String cost;

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
}
