package com.syc.service.impl;

import com.syc.dao.CarDao;
import com.syc.pojo.Car;
import com.syc.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;
    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Override
    public List<Car> selAllCars() {
        return carDao.selAllCars();
    }

    @Override
    public void delCarByID(Integer id) {
        carDao.delCarByID(id);
    }

    @Override
    public Car selCarByID(Integer id) {
        return carDao.selCarByID(id);
    }

    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    @Override
    public List<Car> selCarByMap(Map map) {
        return carDao.selCarByMap(map);
    }
}
