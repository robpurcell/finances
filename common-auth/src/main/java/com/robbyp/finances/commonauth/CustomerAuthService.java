package com.robbyp.finances.commonauth;

import com.robbyp.finances.commonauth.model.QuerySideCustomer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

public class CustomerAuthService {
  private CustomerAuthRepository customerAuthRepository;

  public CustomerAuthService(CustomerAuthRepository customerAuthRepository) {
    this.customerAuthRepository = customerAuthRepository;
  }

  public QuerySideCustomer findByEmail(String email) {
    QuerySideCustomer result = DataAccessUtils.uniqueResult(customerAuthRepository.findByEmail(email));
    if (result == null)
      throw new EmptyResultDataAccessException(1);

    return result;
  }

  public QuerySideCustomer findByEmailAndPassword(String email, String password) {
    QuerySideCustomer result = DataAccessUtils
      .uniqueResult(customerAuthRepository.findByEmailAndPassword(email, password));
    if (result == null)
      throw new EmptyResultDataAccessException(1);

    return result;
  }
}
