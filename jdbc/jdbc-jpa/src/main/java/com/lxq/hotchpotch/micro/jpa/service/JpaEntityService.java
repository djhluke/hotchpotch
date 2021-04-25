package com.lxq.hotchpotch.micro.jpa.service;

import com.lxq.hotchpotch.micro.jpa.pojo.dto.JpaEntityDto;
import com.lxq.hotchpotch.micro.jpa.mapper.JpaEntityMapper;
import com.lxq.hotchpotch.micro.jpa.pojo.po.JpaEntity;
import com.lxq.hotchpotch.micro.jpa.repository.JpaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luxinqiang
 */
@Service
public class JpaEntityService {

    @Autowired
    private JpaEntityRepository jpaEntityRepository;

    @Transactional
    public JpaEntityDto save(JpaEntityDto jpaEntityDto) {
        JpaEntity jpaEntity = JpaEntityMapper.INSTANCES.toJpaEntity(jpaEntityDto);
        JpaEntity save = jpaEntityRepository.save(jpaEntity);
        return JpaEntityMapper.INSTANCES.toJpaEntityDto(save);
    }

    @Transactional
    public void delete(String id) {
        jpaEntityRepository.deleteById(id);
    }

    public JpaEntityDto getById(String id) {
        JpaEntity jpaEntity = jpaEntityRepository.findById(id).get();
        return JpaEntityMapper.INSTANCES.toJpaEntityDto(jpaEntity);
    }

    public List<JpaEntityDto> findAll() {
        List<JpaEntity> list = jpaEntityRepository.findAll();
        return list.stream().map(JpaEntityMapper.INSTANCES::toJpaEntityDto).collect(Collectors.toList());
    }

    public List<JpaEntityDto> findByNameLikeOrderByCreateTimeDesc(String name) {
        List<JpaEntity> list = jpaEntityRepository.findByNameLikeOrderByCreateTimeDesc(name);
        return list.stream().map(JpaEntityMapper.INSTANCES::toJpaEntityDto).collect(Collectors.toList());
    }

}
