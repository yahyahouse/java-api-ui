package com.example.application.model;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {
    private static boolean isLoggedIn = false;
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
