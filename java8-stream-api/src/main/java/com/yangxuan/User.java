package com.yangxuan;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class User {

    public User() {
    }

    public User(Long id, String name, Integer follows, Integer fans) {
        this.id = id;
        this.name = name;
        this.follows = follows;
        this.fans = fans;
    }

    private Long id;

    private String name;
    private String depart;

    private Integer follows;

    private Integer fans;

    private int id1;
    private int id2;
    private String sex;
    private BigDecimal fee;

    public User(int id1, String name, String sex, int id2, String depart, BigDecimal fee) {
        this.id1 = id1;
        this.id2 = id2;
        this.name = name;
        this.sex = sex;
        this.depart = depart;
        this.fee = fee;
    }
}
