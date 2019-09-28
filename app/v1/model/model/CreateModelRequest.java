package v1.model.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateModelRequest {

    private String title;

    @JsonProperty("year_prod_start")
    private Integer yearProdStart;

    @JsonProperty("year_prod_end")
    private Integer yearProdEnd;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearProdStart() {
        return yearProdStart;
    }

    public void setYearProdStart(Integer yearProdStart) {
        this.yearProdStart = yearProdStart;
    }

    public Integer getYearProdEnd() {
        return yearProdEnd;
    }

    public void setYearProdEnd(Integer yearProdEnd) {
        this.yearProdEnd = yearProdEnd;
    }
}
