package com.lxq.hotchpotch.common.mapper.mapper;

import com.lxq.hotchpotch.common.mapper.pojo.dto.SysUserDto;
import com.lxq.hotchpotch.common.mapper.pojo.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author luxinqiang
 */
@Mapper
public interface SysUserMapper {

    /**
     * 获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的
     * 方法都是public abstract 的
     */
    SysUserMapper INSTANCES = Mappers.getMapper(SysUserMapper.class);

    /**
     * 这个方法就是用于实现对象属性复制的方法
     *
     * @param sysUser 这个参数就是源对象，也就是需要被复制的对象
     * @return 返回的是目标对象，就是最终的结果对象
     * @Mapping 用来定义属性复制规则
     * source 指定源对象属性
     * target 指定目标对象属性
     */
    @Mappings({
            @Mapping(source = "password", target = "pwd")
    })
    SysUserDto toSysUserDto(SysUser sysUser);


    @Mappings({
            @Mapping(source = "pwd", target = "password")
    })
    SysUser toSysUser(SysUserDto sysUserDto);

}
