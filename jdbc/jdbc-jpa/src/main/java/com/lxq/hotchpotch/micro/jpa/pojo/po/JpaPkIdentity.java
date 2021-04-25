package com.lxq.hotchpotch.micro.jpa.pojo.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 例子.自增主键
 *
 * @author luxinqiang
 */
@Entity
@Getter
@Setter
public class JpaPkIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
