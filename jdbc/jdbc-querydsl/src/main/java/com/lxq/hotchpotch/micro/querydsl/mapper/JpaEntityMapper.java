package com.lxq.hotchpotch.micro.querydsl.mapper;

import com.lxq.hotchpotch.micro.querydsl.pojo.dto.JpaEntityDto;
import com.lxq.hotchpotch.micro.querydsl.pojo.po.JpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author luxinqiang
 */
@Mapper
public interface JpaEntityMapper {

    JpaEntityMapper INSTANCES = Mappers.getMapper(JpaEntityMapper.class);

    JpaEntityDto toJpaEntityDto(JpaEntity jpaEntity);

    JpaEntity toJpaEntity(JpaEntityDto jpaEntityDto);

}
