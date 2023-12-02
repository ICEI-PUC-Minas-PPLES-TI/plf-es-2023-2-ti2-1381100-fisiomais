package com.fisiomais.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundExecption extends UsernameNotFoundException  {

    public UserNotFoundExecption(String msg, Throwable cause) {
        super(msg, cause);
    }
}
