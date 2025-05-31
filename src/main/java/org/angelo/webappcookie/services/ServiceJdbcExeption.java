package org.angelo.webappcookie.services;

public class ServiceJdbcExeption extends RuntimeException{
    //Recargamos los constructores para obtener el mensaje del error
    //Y otro construtor que nos va a dar el mensaje y la causa del error


    public ServiceJdbcExeption(String message) {
        super(message);
    }

    public ServiceJdbcExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
