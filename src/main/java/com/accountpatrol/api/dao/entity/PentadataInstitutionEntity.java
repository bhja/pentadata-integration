package com.accountpatrol.api.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "pentadata_institution")
public class PentadataInstitutionEntity extends AbstractTimestampEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    public static final String COL_ID = "id";

    @Column(name = "institution_id")
    private String institutionId;

    @Column(name="name")
    private String name;
    public static final String NAME =  "name";

    @Column(name="logo")
    private String logo;

}
