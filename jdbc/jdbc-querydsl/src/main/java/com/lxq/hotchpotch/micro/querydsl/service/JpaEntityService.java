package com.lxq.hotchpotch.micro.querydsl.service;

import com.lxq.hotchpotch.micro.querydsl.mapper.JpaEntityMapper;
import com.lxq.hotchpotch.micro.querydsl.pojo.dto.JpaEntityDto;
import com.lxq.hotchpotch.micro.querydsl.pojo.po.JpaEntity;
import com.lxq.hotchpotch.micro.querydsl.pojo.po.QJpaEntity;
import com.lxq.hotchpotch.micro.querydsl.pojo.po.QSysUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luxinqiang
 */
@Service
public class JpaEntityService {

    private JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager entityManager;

    /***
     * 指定初始化queryFactory
     * <br/>
     * 2021-03-11 17:08
     *
     * @param
     * @return void
     * @author luxinqiang
     */
    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<JpaEntityDto> querydsl(String name, Pageable pageable) {
        QJpaEntity qJpaEntity = QJpaEntity.jpaEntity;
        QSysUser qSysUser = QSysUser.sysUser;
        List<JpaEntity> list = queryFactory.select(qJpaEntity)
                .from(qJpaEntity)
                .leftJoin(qSysUser)
                .on(qJpaEntity.id.eq(qSysUser.id))
                .where(qJpaEntity.name.eq(name))
                .orderBy(qJpaEntity.createTime.desc())
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();
        return list.stream().map(JpaEntityMapper.INSTANCES::toJpaEntityDto).collect(Collectors.toList());
    }

}
