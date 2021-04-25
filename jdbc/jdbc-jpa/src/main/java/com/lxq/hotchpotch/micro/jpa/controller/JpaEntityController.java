package com.lxq.hotchpotch.micro.jpa.controller;

import com.lxq.hotchpotch.common.tool.util.IdCardUtils;
import com.lxq.hotchpotch.common.tool.util.NameUtils;
import com.lxq.hotchpotch.micro.jpa.pojo.dto.JpaEntityDto;
import com.lxq.hotchpotch.micro.jpa.pojo.dto.validate.Validate;
import com.lxq.hotchpotch.micro.jpa.service.JpaEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author luxinqiang
 */
@RestController
@RequestMapping("jpaEntity")
@Slf4j
public class JpaEntityController {

    @Autowired
    private JpaEntityService jpaEntityService;

    @RequestMapping("addRandom")
    public Object addRandom() {
        JpaEntityDto jpaEntityDto = new JpaEntityDto();
        jpaEntityDto.setIdCard(IdCardUtils.generate());
        jpaEntityDto.setName(NameUtils.chineseName());
        jpaEntityDto.setSex(Math.random() < 0.5 ? "0" : "1");
        jpaEntityDto.setBirthday(new Date());
        return jpaEntityService.save(jpaEntityDto);
    }

    @RequestMapping("add")
    public Object add(@RequestBody @Validated(value = Validate.Add.class) JpaEntityDto jpaEntityDto) {
        return jpaEntityService.save(jpaEntityDto);
    }

    @RequestMapping("modify")
    public Object modify(@RequestBody @Validated(value = Validate.Modify.class) JpaEntityDto jpaEntityDto) {
        return jpaEntityService.save(jpaEntityDto);
    }

    @RequestMapping("remove")
    public void remove(@NotBlank(message = "id不能为空") String id) {
        jpaEntityService.delete(id);
    }

    @RequestMapping("one")
    public Object one(@NotBlank(message = "id不能为空") String id) {
        return jpaEntityService.getById(id);
    }

    @RequestMapping("list")
    public Object list() {
        return jpaEntityService.findAll();
    }

    @RequestMapping("listByName")
    public Object listByName(String name) {
        return jpaEntityService.findByNameLikeOrderByCreateTimeDesc(name);
    }

}
