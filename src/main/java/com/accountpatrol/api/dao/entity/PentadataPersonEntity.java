package com.accountpatrol.api.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="pentadata_person")
public class PentadataPersonEntity extends AbstractTimestampEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    public static final String COL_ID = "id";

    @Column(name = "user_id",length=64)
    private String userId;
    public static final String COL_USER_ID = "userId";

    @Column(name = "person_id",length=64)
    private String personId;
    public static final String COL_PERSON_ID = "personId";




}
