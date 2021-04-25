package com.lxq.hotchpotch.micro.querydsl.controller;

import com.lxq.hotchpotch.common.tool.util.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luxinqiang
 */
@RestController
@RequestMapping()
@Slf4j
public class IndexController {

    @RequestMapping()
    public String index() {
        return AddressUtils.localHostAddress();
    }

}
