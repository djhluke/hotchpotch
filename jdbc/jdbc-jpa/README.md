# jpa

* yml配置

```
spring:
    jpa:
        database: mysql     # 数据库类型
        generate-ddl: true      # 默认false，根据实体自动建表
        show-sql: false     # 默认false，控制台打印sql
        #open-in-view: true     # http://codingdict.com/questions/6367
        hibernate:
          ddl-auto: update      # create 启动时删数据库中的表，然后创建，退出时不删除数据表
                                  create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错
                                  update 如果启动时表格式不一致则更新表，原有数据保留
                                     就算设置成update值，也不能识别对表结构的所有更改，往往只能识别出增加的字段，比如修改字段名，修改字段类型或者删除一个字段都是不能够识别的。
                                  validate 项目启动表结构进行校验 如果不一致则报错
                                  none 禁止ddl
          naming:
            physical-strategy: com.lxq.hotchpotch.micro.study.config.jpa.NormalPhysicalNamingStrategy      # 物理名称命名策略
        properties:
          hibernate:
            #dialect: org.hibernate.dialect.MySQL5InnoDBDialect
            format_sql: true        # 格式化sql
```

* 实体配置  
  com.lxq.hotchpotch.micro.study.entity.jpa  
  表、字段和主键
* asd

```
建表的时候加表备注，不好用，大小写敏感
@org.hibernate.annotations.Table(appliesTo = "jpa_entity_conf", comment = "用例字段类型")      // EntityBinder.processComplementaryTableDefinitions()
```

