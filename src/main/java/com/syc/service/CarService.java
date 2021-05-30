package com.syc.service;

import com.syc.pojo.Car;

import java.util.List;
import java.util.Map;

public interface CarService {
    void addCar(Car car);
    List<Car> selAllCars();
    void delCarByID(Integer id);//通过id删除车辆
    Car selCarByID(Integer id);//通过id查询车辆
    void updateCar(Car car);
    List<Car> selCarByMap(Map map);
}
