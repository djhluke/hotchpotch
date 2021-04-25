package com.lxq.hotchpotch.micro.querydsl.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxq.hotchpotch.micro.querydsl.pojo.dto.validate.Validate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luxinqiang
 */
@Getter
@Setter
public class JpaEntityDto {

    @NotBlank(groups = {Validate.Modify.class}, message = "id不能为空")
    private String id;

    @NotBlank(groups = {Validate.Add.class, Validate.Modify.class}, message = "身份证不能为空")
    private String idCard;

    @NotBlank(groups = {Validate.Add.class, Validate.Modify.class}, message = "姓名不能为空")
    private String name;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Integer age;

    private BigDecimal money;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String remarks;

}
