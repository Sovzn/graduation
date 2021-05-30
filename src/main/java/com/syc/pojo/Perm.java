package com.syc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perm {
    private Integer pid;
    private String perms;
    private Integer rid;
}
