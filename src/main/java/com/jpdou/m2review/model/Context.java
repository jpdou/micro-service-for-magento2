package com.jpdou.m2review.model;

public class Context {

    private static Context instance;
    private boolean isLogged;

    private Context() {
        this.isLogged = false;
    }

    public static Context getInstance()
    {
        if (Context.instance == null) {
            Context.instance = new Context();
        }
        return Context.instance;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
