package com.lxq.hotchpotch.micro.querydsl.pojo.po;

import com.lxq.hotchpotch.micro.querydsl.pojo.po.base.DataBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author luxinqiang
 */
@Entity
@Table(name = "sys_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Getter
@Setter
public class SysUser extends DataBase {

    @Column(name = "user_name", length = 64, unique = true, nullable = false)
    @NotBlank(message = "userName不能为null")
    private String userName;

    @Column(name = "loginName", length = 64, unique = true, nullable = false)
    @NotBlank(message = "loginName不能为null")
    private String loginName;

    @Column(name = "password", length = 255, nullable = false)
    @NotBlank(message = "password不能为null")
    private String password;

}
