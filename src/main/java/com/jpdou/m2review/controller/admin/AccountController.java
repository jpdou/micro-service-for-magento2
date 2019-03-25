package com.jpdou.m2review.controller.admin;

import com.jpdou.m2review.exception.NoSuchEntityException;
import com.jpdou.m2review.model.Account;
import com.jpdou.m2review.model.AuthorizeService;
import com.jpdou.m2review.model.Context;
import com.jpdou.m2review.model.http.Response;
import com.jpdou.m2review.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("admin/account/login")
    public String login() {
        return "admin/account/login";
    }

    @PostMapping("/admin/account/loginPost")
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

    @PostMapping("/admin/account/registerPost")
    public Response registerPost(
            @RequestParam(value="email", defaultValue="") String email,
            @RequestParam(value="password", defaultValue = "") String password,
            @RequestParam(value="password_repeat", defaultValue = "") String passwordRepeat
    ) {
        Response response = new Response();

        try {
            this.accountRepository.findByEmail(email);

            response.setMessage("This email was already registered.");
            return response;
        } catch (NoSuchEntityException e) {

        }

        if (password.equals(passwordRepeat)) {
            Account account = new Account();
            account.setEmail(email);

            String salt = this.authorizeService.getRandomSalt(4);
            String passwordHash = this.authorizeService.hashPassword(password, salt);

            if (passwordHash.length() == 0) {
                response.setMessage("An error occurred, please try again.");
                return response;
            }

            account.setSalt(salt);
            account.setPasswordHash(passwordHash);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            account.setCreatedAt(timestamp.toString());

            this.accountRepository.save(account);
            response.setStatus(true);
        } else {
            response.setMessage("Please make sure the password and the password repeat is the same.");
        }

        return response;
    }

}
