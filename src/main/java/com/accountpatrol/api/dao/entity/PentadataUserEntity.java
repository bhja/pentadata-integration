package com.accountpatrol.api.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="pentadata_user")
public class PentadataUserEntity extends AbstractTimestampEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    public static final String COL_ID = "id";

    @Column(name="email_id")
    private String emailId;
    public static final String EMAIL_ID="emailId";




}
