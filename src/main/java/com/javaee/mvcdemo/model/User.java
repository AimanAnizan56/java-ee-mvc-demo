package com.javaee.mvcdemo.model;

import javax.validation.constraints.*;

public class User {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Email
    private String email;

    public User(@NotNull String username,@NotNull @Min(6) String password,@Email String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
