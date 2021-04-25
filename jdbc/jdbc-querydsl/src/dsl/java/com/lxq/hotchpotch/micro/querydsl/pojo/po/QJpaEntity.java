package com.lxq.hotchpotch.micro.querydsl.pojo.po;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaEntity is a Querydsl query type for JpaEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJpaEntity extends EntityPathBase<JpaEntity> {

    private static final long serialVersionUID = 1261732762L;

    public static final QJpaEntity jpaEntity = new QJpaEntity("jpaEntity");

    public final com.lxq.hotchpotch.micro.querydsl.pojo.po.base.QDataBase _super = new com.lxq.hotchpotch.micro.querydsl.pojo.po.base.QDataBase(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final StringPath id = _super.id;

    public final StringPath idCard = createString("idCard");

    public final NumberPath<java.math.BigDecimal> money = createNumber("money", java.math.BigDecimal.class);

    public final StringPath name = createString("name");

    //inherited
    public final StringPath remarks = _super.remarks;

    public final StringPath sex = createString("sex");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public QJpaEntity(String variable) {
        super(JpaEntity.class, forVariable(variable));
    }

    public QJpaEntity(Path<? extends JpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaEntity(PathMetadata metadata) {
        super(JpaEntity.class, metadata);
    }

}

