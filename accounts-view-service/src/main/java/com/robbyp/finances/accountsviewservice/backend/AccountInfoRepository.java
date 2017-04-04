package com.robbyp.finances.accountsviewservice.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface AccountInfoRepository extends MongoRepository<AccountInfo, String> {

    List<AccountInfo> findByCustomerId(String customerId);
}
