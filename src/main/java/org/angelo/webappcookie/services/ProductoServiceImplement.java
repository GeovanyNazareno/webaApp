package org.angelo.webappcookie.services;

import org.angelo.webappcookie.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "Laptop", "computacion", 523.21),
                new Producto(3L, "Mouse", "inalambrico", 15.25),
                new Producto(3L, "Impresora", "tinta continua", 256.25)
        );
    }
}
