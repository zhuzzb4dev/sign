package com.zhuzimo.sign;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PtEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String key;
    private String pin;
}
