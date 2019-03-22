package com.jpdou.m2review.controller.admin;

import com.jpdou.m2review.exception.NoSuchEntityException;
import com.jpdou.m2review.model.Account;
import com.jpdou.m2review.model.AuthorizeService;
import com.jpdou.m2review.model.Context;
import com.jpdou.m2review.model.Messager;
import com.jpdou.m2review.model.http.Response;
import com.jpdou.m2review.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Messager messager;

    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/admin/loginPost")
    public Response loginPost(
        @RequestParam(value="email", defaultValue="") String email,
        @RequestParam(value="password", defaultValue = "") String password
    ) {
        Response response = new Response();

        Context context = Context.getInstance();
        if (!context.isLogged()) {
            try {
                Account account = this.accountRepository.findByEmail(email);

                this.authorizeService.auth(account, password);

                if (context.isLogged()) {
                    response.setStatus(context.isLogged());
                    response.setMessage("Welcome!");
                } else {
                    response.setMessage("Your password is wrong, login failed!");
                }
            } catch (NoSuchEntityException e) {
                response.setMessage("Sorry, we have not found your account according your email.");
            }
        }
        return response;
    }

    public Response registerPost(
            @RequestParam(value="email", defaultValue="") String email,
            @RequestParam(value="password", defaultValue = "") String password,
            @RequestParam(value="password_repeat", defaultValue = "") String passwordRepeat

    ) {
        Response response = new Response();

        try {
            Account account = this.accountRepository.findByEmail(email);

            response.setMessage("This email was already registered.");
            return response;
        } catch (NoSuchEntityException e) {

        }

        if (password.equals(passwordRepeat)) {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);

            String salt = this.authorizeService.getRandomSalt();
            String passwordHash = this.authorizeService.hashPassword(account.getPassword(), salt);

            if (passwordHash.length() == 0) {
                response.setMessage("An error occurred, please try again.");
                return response;
            }

            account.setSalt(salt);
            account.setPasswordHash(passwordHash);

            this.accountRepository.save(account);
        } else {
            response.setMessage("Please make sure the password and the password repeat is the same.");
        }

        return response;
    }

}
