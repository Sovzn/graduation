package com.syc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syc.pojo.Car;
import com.syc.pojo.User;
import com.syc.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.MalformedObjectNameException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    @Autowired
    CarService carService;
    @RequestMapping("/toaddcar")
public String toaddcar(){  //跳转到添加车辆页面
        return "page-addcar";
    }

    @PostMapping ("/addcar")
    public String addcar(Car car){  //添加车辆
        carService.addCar(car);
        return "redirect:/selAllCars";
    }

    @RequestMapping("/selAllCars")//车辆列表界面
    public String selAllCars(Model model, @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue ="8") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<Car> cars=  carService.selAllCars();
        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(cars,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data-cars";
    }
    @RequestMapping("/selAllCarsByUser")//跳转到用户列表界面
    public String selAllCarsByUser(Model model, @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue ="8") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<Car> cars=  carService.selAllCars();
        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(cars,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data-cars-user";
    }

    @RequestMapping("/delCarByID/{id}")
    public String delCarByID(@PathVariable Integer id){  //跳转到添加车辆页面
        carService.delCarByID(id);
        return "redirect:/selAllCars";
    }
    //跳转到编辑车辆界面(管理员)
    @RequestMapping("/toupdatecar/{id}")
    public String toupdatecar(Model model,@PathVariable Integer id){
        Car car=  carService.selCarByID(id);
        model.addAttribute("updatecar",car);
        return "page-updatecar";
    }
    //
    @PostMapping("/updatecar")
    public String updatecar(Car car){
        carService.updateCar(car);
        return "redirect:/selAllCars";
    }
    //根据条件查询车辆
    @PostMapping("selCarByMap")
    public String selUserByMap(Model model,@RequestParam String cnumber,@RequestParam String cseries,@RequestParam String state,
                               @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue ="8") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        Map<String,String> map=new HashMap<>();
        map.put("cnumber",cnumber);
        map.put("cseries",cseries);
        map.put("state",state);
        List<Car> cars=  carService.selCarByMap(map);
        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(cars,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data-cars";
    }
    //根据条件查询车辆（用户）
    @PostMapping("selCarByMapByUser")
    public String  selCarByMapByUser(Model model,@RequestParam String cnumber,@RequestParam String cseries,@RequestParam String state  ,
                                     @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue ="8") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        Map<String,String> map=new HashMap<>();
        map.put("cnumber",cnumber);
        map.put("cseries",cseries);
        map.put("state",state);
        List<Car> cars=  carService.selCarByMap(map);
        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(cars,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data-cars-user";
    }

}
