package v1.model.entity;

import v1.model.model.CreateModelRequest;

public class Model {

    private Long id;
    private String title;
    private Integer yearProdStart;
    private Integer yearProdEnd;

    public Model() {
    }

    public Model(CreateModelRequest createModelRequest) {
        setTitle(createModelRequest.getTitle());
        setYearProdStart(createModelRequest.getYearProdStart());
        setYearProdEnd(createModelRequest.getYearProdEnd());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
