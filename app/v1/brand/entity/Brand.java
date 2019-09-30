package v1.brand.entity;

import v1.brand.model.CreateBrandRequest;

public class Brand {

    private Long id;
    private String name;
    private String country;

    public Brand() {
    }

    public Brand(CreateBrandRequest request) {
        setName(request.getName());
        setCountry(request.getCountry());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
