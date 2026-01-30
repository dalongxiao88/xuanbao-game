package org.come.bean;

import org.come.entity.Car;

import java.util.List;

public class CarResult
{
    private List<Car> cars;
    
    public List<Car> getCars() {
        return this.cars;
    }
    
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
