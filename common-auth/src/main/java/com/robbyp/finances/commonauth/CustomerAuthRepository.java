package com.robbyp.finances.commonauth;

import com.robbyp.finances.commonauth.model.QuerySideCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface CustomerAuthRepository extends MongoRepository<QuerySideCustomer, String> {

  List<QuerySideCustomer> findByEmail(String email);

  List<QuerySideCustomer> findByEmailAndPassword(String email, String password);
}
