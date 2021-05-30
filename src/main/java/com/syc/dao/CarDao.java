package com.syc.dao;

import com.syc.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CarDao {
    void addCar(Car car);//添加车辆
    List<Car> selAllCars();//查询所有车辆
    void delCarByID(Integer id);//通过id删除车辆
    Car selCarByID(Integer id);//通过id查询车辆
    void updateCar(Car car);
    List<Car> selCarByMap(Map map);
}
