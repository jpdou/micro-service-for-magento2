package com.jpdou.m2review.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password_hash;
    private float credit_balance;
    private Integer package_type;
    private String created_at;
    private String updated_at;

    @Autowired
    private Messager messager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public float getCredit_balance() {
        return credit_balance;
    }

    public void setCredit_balance(float credit_balance) {
        this.credit_balance = credit_balance;
    }

    public int getPackage_type() {
        return package_type;
    }

    public void setPackage_type(int package_type) {
        this.package_type = package_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void auth(String password)
    {
        Context context = Context.getInstance();

        if (!context.isLogged()) {
            try {
                MessageDigest md = MessageDigest.getInstance("KEY_MD5");
                byte[] passwordBytes = password.getBytes();
                md.update(passwordBytes);
                String passwordHash = new String(md.digest());

                boolean logged = this.password_hash.equals(passwordHash);

                context.setLogged(logged);

                if (logged) {
                    this.messager.addSuccess("Welcome!");
                } else {
                    this.messager.addError("Your password is wrong, login failed!");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
