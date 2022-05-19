package com.accountpatrol.api.dao.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractTimestampEntity implements Serializable{

    @Column(nullable=false)
    private Long created;
    public static final String CREATED = "created";

    @Column(nullable=false)
    private Long updated;
    public static final String UPDATED = "updated";

    public AbstractTimestampEntity() {
        updated = created = System.currentTimeMillis();
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

}
