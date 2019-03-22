package com.jpdou.m2review.model;

import com.jpdou.m2review.exception.NoSuchEntityException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Messager {
    public static final String MESSAGE_TYPE_ERROR = "error";
    public static final String MESSAGE_TYPE_SUCCESS = "success";
    public static final String MESSAGE_TYPE_WARNING = "warning";

    private ArrayList<String> error;
    private ArrayList<String> success;
    private ArrayList<String> warning;

    public Messager()
    {
        this.error = new ArrayList<>();
        this.success = new ArrayList<>();
        this.warning = new ArrayList<>();
    }

    public void addSuccess(String message)
    {
        this.success.add(message);
    }

    public void addError(String message)
    {
        this.error.add(message);
    }

    public void addWarning(String message)
    {
        this.warning.add(message);
    }

    public boolean has(String type) {
        boolean has;
        try {
            has = this.getListByType(type).size() > 0;
        } catch (NoSuchEntityException e) {
            has = false;
        }
        return has;
    }

    public ArrayList<String> flush(String type)
    {
        ArrayList<String> messages = new ArrayList<>();
        try {
            messages = new ArrayList<>(this.getListByType(type));
            this.getListByType(type).clear();
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void clean(String type)
    {
        try {
            this.getListByType(type).clear();
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getListByType(String type) throws NoSuchEntityException
    {

        switch (type) {
            case MESSAGE_TYPE_SUCCESS:
                return this.success;
            case MESSAGE_TYPE_ERROR:
                return this.error;
            case MESSAGE_TYPE_WARNING:
                return this.warning;
            default:
                break;
        }

        throw new NoSuchEntityException("Unrecognized message list \"type\"");
    }
}
