package com.syc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Integer cid;//车id
    private String cnumber;//车牌号
    private String ctype; //车型
    private Integer coutNum;//出车次数
    private Integer cage;//车龄
    private Integer seatnum; //座位数量
    private String state;//状态
    private String cseries;//车系
}
