package v1.car.model;

import v1.car.entity.CarExt;

import java.util.List;

public class ListCarResponse {

    private List<CarExt> listCar;

    public ListCarResponse(List<CarExt> listCar) {
        this.listCar = listCar;
    }

    public List<CarExt> getListCar() {
        return listCar;
    }

}
