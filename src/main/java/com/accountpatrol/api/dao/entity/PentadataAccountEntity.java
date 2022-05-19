package com.accountpatrol.api.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="pentadata_account")
@NamedNativeQueries({
        @NamedNativeQuery(name = "getAccountsByUser",
                query = "select * from pentadata_account where user_id = :user_id ", resultClass = PentadataAccountEntity.class)})

public class PentadataAccountEntity extends AbstractTimestampEntity  {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    public static final String COL_ID = "id";

    @Column(name="bank")
    private String bank;

    @Column(name="account_id")
    private Integer accountId;
    public static final String COL_ACCOUNT_ID = "accountId";

    @Column(name="person_id")
    private String personId;
    public static final String COL_PERSON_ID = "personId";

    @Column(name="user_id")
    private String userId;
    public static final String COL_USER_ID = "userId";

    @Column(name="routing")
    private Integer routing;

    @Column(name="last_opt_in")
    private String lastOptIn;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_type")
    private String accountType;








}
