package com.accountpatrol.api.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="pentadata_transaction")
public class PentadataTransactionEntity  extends AbstractTimestampEntity {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", length = 64)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    public static final String COL_ID = "id";

    @Column(name = "account_id")
    private Integer accountId;
    public static final String COL_ACCOUNT_ID = "accountId";

    @Column(name = "person_id")
    private String personId;
    public static final String COL_PERSON_ID = "personId";

    @Column(name = "user_id")
    private String userId;
    public static final String COL_USER_ID = "userId";

    @Column(name="description")
    private String description;

    @Column(name="datetime")
    private String dateTime;

    @Column(name="transaction_date")
    private Date transactionDate;

    @Column(name="amount")
    private Double amount;

    @Column(name="merchantName")
    private String merchantName;

    @Column(name="category")
    private String category;

    @Column(name="currency")
    private String currency;

    @Column(name="pending")
    private Boolean pending;

    @Column(name="transactionId")
    private String transactionId;

    @Column(name="cid")
    private String cId;

    @Column(name="location")
    private String location;


}
