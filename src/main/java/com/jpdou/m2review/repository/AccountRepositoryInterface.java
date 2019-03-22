package com.jpdou.m2review.repository;

import org.springframework.data.repository.CrudRepository;
import com.jpdou.m2review.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
interface AccountRepositoryInterface extends CrudRepository<Account, Integer> {

    ArrayList<Account> findByEmail(String email);

}
