package com.jpdou.m2review.model;

import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            String passwordHash = this.hashPassword(account.getPassword(), account.getSalt());
            context.setLogged(account.getPasswordHash().equals(passwordHash));
        }
    }

    public String getRandomSalt()
    {
        byte[] array = new byte[8]; // length is bounded by 8
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    public String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("KEY_MD5");
            byte[] passwordBytes = password.concat(salt).getBytes();
            md.update(passwordBytes);
            return new String(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
