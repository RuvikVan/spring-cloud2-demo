package com.rv.auth.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ruvikvan
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return true;
//        return s.equals(charSequence.toString());
    }

}
