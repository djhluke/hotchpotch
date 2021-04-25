package com.lxq.hotchpotch.micro.querydsl.pojo.po;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysUser is a Querydsl query type for SysUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysUser extends EntityPathBase<SysUser> {

    private static final long serialVersionUID = -1224644812L;

    public static final QSysUser sysUser = new QSysUser("sysUser");

    public final com.lxq.hotchpotch.micro.querydsl.pojo.po.base.QDataBase _super = new com.lxq.hotchpotch.micro.querydsl.pojo.po.base.QDataBase(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath id = _super.id;

    public final StringPath loginName = createString("loginName");

    public final StringPath password = createString("password");

    //inherited
    public final StringPath remarks = _super.remarks;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public final StringPath userName = createString("userName");

    public QSysUser(String variable) {
        super(SysUser.class, forVariable(variable));
    }

    public QSysUser(Path<? extends SysUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUser(PathMetadata metadata) {
        super(SysUser.class, metadata);
    }

}

