package com.lxq.hotchpotch.common.mapper;

import com.lxq.hotchpotch.common.mapper.mapper.SysUserMapper;
import com.lxq.hotchpotch.common.mapper.pojo.dto.SysUserDto;
import com.lxq.hotchpotch.common.mapper.pojo.entity.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

public class CommonMapperApplicationTests {

    private SysUserDto sysUserDto;

    @Before
    public void setup() {
        sysUserDto = new SysUserDto();
        sysUserDto.setUserName("超管");
        sysUserDto.setLoginName("admin");
        sysUserDto.setPwd("123456");
    }

    /**
     * 2021-04-25 9:29
     * mapstruct用法
     *
     * @param
     * @return void
     * @author luxinqiang
     */
    @Test
    public void mapstruct() {
        SysUser sysUser = SysUserMapper.INSTANCES.toSysUser(sysUserDto);
        System.out.println(sysUser);
    }

    /**
     * 2021-04-25 9:29
     * modelmapper用法
     *
     * @param
     * @return void
     * @author luxinqiang
     */
    @Test
    public void modelmapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFullTypeMatchingRequired(true)      // 全匹配
                .setMatchingStrategy(MatchingStrategies.STRICT);        // 严格匹配
        modelMapper.addMappings(new PropertyMap<SysUserDto, SysUser>() {
            @Override
            protected void configure() {
                // 使用自定义转换规则
                map(source.getPwd(), destination.getPassword());
            }
        });
        SysUser sysUser = modelMapper.map(sysUserDto, SysUser.class);
        System.out.println(sysUser);
    }

}
