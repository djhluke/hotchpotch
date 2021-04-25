package com.lxq.hotchpotch.micro.jpa.config.jpa.naming.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * spring配置项:spring.jpa.hibernate.naming.physical-strategy
 * <br/>
 * 自定义默认schema名:${my.jpa.default-schema-name}
 * <br/>
 * 1.{@link org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl}直接映射,不会做过多的处理
 * <br/>
 * 2.默认为{@link org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy},表名,字段为小写,当有大写字母的时候会添加下划线分隔符号
 *
 * @author luxinqiang
 */
@Slf4j
public class BasePhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    // todo
    // 不在容器内，无法注入@Value，暂时放置
    @Value("${my.jpa.default-schema-name:''}")
    private String defaultSchemaName;

    /**
     * 自定义默认模式名
     * <br/>
     * 2021-01-25 11:42
     *
     * @param name            模式名
     * @param jdbcEnvironment jdbcEnvironment
     * @return org.hibernate.boot.model.naming.Identifier
     * @author luxinqiang
     */
    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        log.info(this.getClass().getSimpleName() + " : " + this.getClass().getName());
        if (name == null || StringUtils.isBlank(name.getText())) {
            if (StringUtils.isNotBlank(this.defaultSchemaName)) {
                name = Identifier.toIdentifier(this.defaultSchemaName);
            }
        }
        return super.toPhysicalSchemaName(name, jdbcEnvironment);
    }

}