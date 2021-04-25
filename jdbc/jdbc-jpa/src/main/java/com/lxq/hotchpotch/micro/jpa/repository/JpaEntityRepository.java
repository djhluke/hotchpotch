package com.lxq.hotchpotch.micro.jpa.repository;

import com.lxq.hotchpotch.micro.jpa.pojo.po.JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luxinqiang
 */
@Repository
public interface JpaEntityRepository extends JpaRepository<JpaEntity, String> {

    List<JpaEntity> findByNameLikeOrderByCreateTimeDesc(String name);

}
