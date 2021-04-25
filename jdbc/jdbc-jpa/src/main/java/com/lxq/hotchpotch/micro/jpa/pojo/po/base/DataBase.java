package com.lxq.hotchpotch.micro.jpa.pojo.po.base;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author luxinqiang
 */
@MappedSuperclass
@Getter
@Setter
public class DataBase implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "create_by", length = 64)
    private String createBy;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_by", length = 64)
    private String updateBy;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Lob
    @Column(name = "remarks")
    private String remarks;

}
