package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString(includeFieldNames=true)
public class Dog {

    private int id;
    private String name;
    private String country;
    private String owner;

}
