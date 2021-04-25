package com.lxq.hotchpotch.micro.jpa.mapper;

import com.lxq.hotchpotch.micro.jpa.pojo.dto.JpaEntityDto;
import com.lxq.hotchpotch.micro.jpa.pojo.po.JpaEntity;
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
