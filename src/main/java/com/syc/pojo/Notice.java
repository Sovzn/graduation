package com.syc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer nid;//通知id
    private String createusername;//创建者登录名
    private String createname;//创建者姓名
    private Integer received;
    private String content;//通知内容
}
