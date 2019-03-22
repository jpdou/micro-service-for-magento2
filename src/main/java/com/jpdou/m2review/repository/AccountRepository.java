package com.jpdou.m2review.repository;

import com.jpdou.m2review.exception.NoSuchEntityException;
import com.jpdou.m2review.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AccountRepository {
    @Autowired
    private AccountRepositoryInterface crudRepository;

    public Account find(int id) throws NoSuchEntityException
    {
        Optional<Account> o = this.crudRepository.findById(id);
        if (o.isPresent()) {
            return o.get();
        } else {
            throw new NoSuchEntityException();
        }
    }

    public Account findByEmail(String email) throws NoSuchEntityException
    {
        ArrayList<Account> list = this.crudRepository.findByEmail(email);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            throw new NoSuchEntityException();
        }
    }

    public void save(Account account) {
        this.crudRepository.save(account);
    }
}
