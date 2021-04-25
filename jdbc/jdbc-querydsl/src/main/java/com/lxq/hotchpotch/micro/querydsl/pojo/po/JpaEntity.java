package com.lxq.hotchpotch.micro.querydsl.pojo.po;

import com.lxq.hotchpotch.micro.querydsl.pojo.po.base.DataBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luxinqiang
 */
@Entity
@Table(name = "jpa_entity")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Getter
@Setter
public class JpaEntity extends DataBase {

    @Column(name = "id_card", length = 18, unique = true, nullable = false)
    @NotBlank(message = "idCard不能为null")
    private String idCard;

    @Column(name = "name", length = 64, nullable = false)
    @NotBlank(message = "name不能为null")
    private String name;

    @Column(name = "sex", length = 1)
    private String sex;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "age")
    private Integer age;

    @Column(name = "money", precision = 20, scale = 4)
    private BigDecimal money;

}
