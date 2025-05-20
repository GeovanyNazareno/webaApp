package services;

import jakarta.servlet.http.Cookie;

import java.lang.reflect.Array;
import java.util.Optional;


public class LoginServiceImplement implements LoginService {

    @Override
    public Optional<String> getUserName() {
        //Obtenemos la cookie
        Cookie[] cookies = req.getCookies() !=null ? req.getCookies() : new Cookie[0];
        return Array.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                //convertimos la cookie en String
                .map(Cookie::getName)
                .findAny();
    }
}
