package com.jpdou.m2review.repository;

import org.springframework.data.repository.CrudRepository;
import com.jpdou.m2review.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
