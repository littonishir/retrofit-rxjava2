package com.ishir.retrofit;

/**
 * Created by littonishir on 2017/10/20.
 */

public class LoginBean {

    /**
     * data : 登录失败
     * name : ishir
     * password : 1238
     * title : koa2 json
     */

    private String data;
    private String name;
    private String password;
    private String title;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
