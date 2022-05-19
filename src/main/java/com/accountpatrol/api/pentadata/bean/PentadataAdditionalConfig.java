package com.accountpatrol.api.pentadata.bean;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PentadataAdditionalConfig {

    @Value("${pentadata.transaction.fromDate}")
    private String fromDate;

    @Value("${pentadata.transaction.pending:true}")
    private boolean pending;

    @Value("${pentadata.scheduled.transaction.fromDate}")
    private String scheduledFromDate;

}
