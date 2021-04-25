package com.lxq.hotchpotch.micro.querydsl.pojo.po.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataBase is a Querydsl query type for DataBase
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QDataBase extends EntityPathBase<DataBase> {

    private static final long serialVersionUID = -2044786092L;

    public static final QDataBase dataBase = new QDataBase("dataBase");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath remarks = createString("remarks");

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QDataBase(String variable) {
        super(DataBase.class, forVariable(variable));
    }

    public QDataBase(Path<? extends DataBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataBase(PathMetadata metadata) {
        super(DataBase.class, metadata);
    }

}

