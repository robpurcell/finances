package com.robbyp.finances.accountsview.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface AccountInfoRepository extends MongoRepository<AccountInfo, String> {

    List<AccountInfo> findByCustomerId(String customerId);
}
