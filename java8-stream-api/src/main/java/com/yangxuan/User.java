package com.yangxuan;

import lombok.Data;
import lombok.ToString;

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

    private Integer follows;

    private Integer fans;

}
