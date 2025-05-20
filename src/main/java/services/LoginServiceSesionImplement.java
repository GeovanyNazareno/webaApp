package services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSesionImplement implements LoginService {

    @Override
    public Optional<String> getUserName(HttpServletRequest request){
        //Obtengo la secion
        HttpSession session = request.getSession();
        //Convertimos el objeto de tipo session a String
        String username = (String) session.getAttribute("username");

        /*
        * implemento una condicional en la cual valido
        * si al obtener el username es distinto de nulo
        * caso contrario devuelvo la sesion vacia*/
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }

}
