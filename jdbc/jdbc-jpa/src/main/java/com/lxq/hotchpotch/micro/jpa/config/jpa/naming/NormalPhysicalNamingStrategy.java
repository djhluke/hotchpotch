package com.lxq.hotchpotch.micro.jpa.config.jpa.naming;

import com.lxq.hotchpotch.micro.jpa.config.jpa.naming.base.BasePhysicalNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 表名,字段不变,当有大写字母的时候会添加下划线分隔符号
 *
 * @author luxinqiang
 */
@Slf4j
public class NormalPhysicalNamingStrategy extends BasePhysicalNamingStrategy {

    @Override
    protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        log.debug(this.getClass().getSimpleName() + " : " + this.getClass().getName());
        return new Identifier(name, quoted);
    }

}