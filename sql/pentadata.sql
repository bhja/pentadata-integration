--Pentadata Person
DROP TABLE IF EXISTS `pentadata_person`;
CREATE TABLE `pentadata_person` (
                                    `id` varchar(64) NOT NULL,
                                    `created` bigint(20) NOT NULL,
                                    `updated` bigint(20) NOT NULL,
                                    `user_id` varchar(255) DEFAULT NULL,
                                    `person_id` varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--Pentadata account
DROP TABLE IF EXISTS `pentadata_account`;
CREATE TABLE `pentadata_account` (
                                     `id` varchar(64) NOT NULL,
                                     `created` bigint(20) NOT NULL,
                                     `updated` bigint(20) NOT NULL,
                                     `added_date` datetime DEFAULT NULL,
                                     `person_id` varchar(255) DEFAULT NULL,
                                     `bank` varchar(255) NOT NULL,
                                     `rounting` varchar(255) DEFAULT NULL,
                                     `account_type` varchar(64) NOT NULL,
                                     `account_id` bigint(20) DEFAULT NULL,
                                     `last_opt_in` datetime DEFAULT NULL
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

---Transactions

DROP TABLE IF EXISTS `pentadata_transactions`;
CREATE TABLE `pentadata_transactions` (
                                     `id` varchar(64) NOT NULL,
                                     `created` bigint(20) NOT NULL,
                                     `updated` bigint(20) NOT NULL,
                                     `added_date` datetime DEFAULT NULL,
                                     `account_id` varchar(255) DEFAULT NULL,
                                     `transaction_date` bigint(100) NOT NULL,
                                     `amount` double NOT NULL,
                                     `account_id` bigint(20) DEFAULT NULL,
                                     `person_id` varchar(255) NOT NULL,
                                     `user_id` varchar(255) NOT NULL,
                                     `description` varchar(255)  DEFAULT NULL,
                                     `merchantName` varchar(255)  DEFAULT NULL,
                                     `category` JSON,
                                     `currency` varchar(255)  DEFAULT NULL,
                                     `pending` BOOLEAN,
                                     `transactionId` bigint(20) NOT NULL,
                                     `cid` bigint(20) NOT NULL ,
                                     `location` JSON DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;