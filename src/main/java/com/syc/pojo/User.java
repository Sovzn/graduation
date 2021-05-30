package com.syc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private  String password;
    private String name;
    private String sex;
    private String phone;
    private String strDate;
    private Integer age;
    private  String company;
    private  Integer rid;
    private Role role;
    private Integer states;
    private Integer yewustates;

}
