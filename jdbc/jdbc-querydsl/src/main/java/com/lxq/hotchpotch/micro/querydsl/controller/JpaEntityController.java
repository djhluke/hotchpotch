package com.lxq.hotchpotch.micro.querydsl.controller;

import com.lxq.hotchpotch.micro.querydsl.service.JpaEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luxinqiang
 */
@RestController
@RequestMapping("jpaEntity")
@Slf4j
public class JpaEntityController {

    @Autowired
    private JpaEntityService jpaEntityService;

    @RequestMapping("querydsl")
    public Object querydsl(String name, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("page - {}, size - {}, sort - {}", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return jpaEntityService.querydsl(name, pageable);
    }

}
