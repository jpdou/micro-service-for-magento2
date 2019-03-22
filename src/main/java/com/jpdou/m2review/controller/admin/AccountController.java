package com.jpdou.m2review.controller.admin;

import com.jpdou.m2review.exception.NoSuchEntityException;
import com.jpdou.m2review.model.Account;
import com.jpdou.m2review.model.Context;
import com.jpdou.m2review.model.Messager;
import com.jpdou.m2review.model.http.Response;
import com.jpdou.m2review.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AccountController {



    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Messager messager;

    @PostMapping("/admin/loginPost")
    public Response loginPost(
        @RequestParam(value="email", defaultValue="") String email,
        @RequestParam(value="password", defaultValue = "") String password
    ) {
        Response response = new Response();

        try {
            Account account = this.accountRepository.findByEmail(email);

            account.auth(password);

            Context context = Context.getInstance();
            if (context.isLogged()) {
                response.setStatus(context.isLogged());
                if (this.messager.has(Messager.MESSAGE_TYPE_SUCCESS)) {
                    ArrayList<String> messages = this.messager.flush(Messager.MESSAGE_TYPE_SUCCESS);
                    response.setMessage(messages);
                }

            } else {
                response.setStatus(context.isLogged());
                response.setMessage(this.messager.flush());
            }
        } catch (NoSuchEntityException e) {
            this.messager.addError("Sorry, we have not found your account according your email.");
        }
    }

}
