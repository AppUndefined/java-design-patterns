package com.iluwatar.prototype;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: java-design-patterns
 * @description
 * @author: Ls
 * @create: 2020-01-10 10:48
 **/
@Data
public class User  implements Serializable {
    private String name;
    private String age;
    private String phone;
    private String address;
    private String password;
    private String uuid;
    private String position;
}
