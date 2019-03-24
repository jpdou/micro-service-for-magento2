package com.jpdou.m2review.model;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import java.util.Random;

@Component
public class AuthorizeService {

    /**
     * 鉴权
     * @param account Account
     * @param password String
     */
    public void auth(Account account, String password)
    {
        Context context = Context.getInstance();

        if (!context.isLogged()) {
            String passwordHash = this.hashPassword(password, account.getSalt());
            context.setLogged(account.getPasswordHash().equals(passwordHash));
        }
    }

    public String getRandomSalt(int len) {
        String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < len;i++) {
            int r = new Random().nextInt(pool.length());
            stringBuilder.append(pool.charAt(r));
        }
        return stringBuilder.toString();
    }

    public String hashPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex(password.concat(salt).getBytes());
    }
}
