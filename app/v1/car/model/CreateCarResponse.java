package v1.car.model;

import v1.car.entity.Car;

public class CreateCarResponse extends CarResponse {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
