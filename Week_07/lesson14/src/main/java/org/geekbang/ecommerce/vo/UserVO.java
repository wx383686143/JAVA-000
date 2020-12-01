package org.geekbang.ecommerce.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {
    private Long pk;
    private String userName;
    private String realName;
    private String Sex;
    private Integer age;
    private Long phone;
    private String address;
    private Date createdTime;
    private Date updateTime;
}
