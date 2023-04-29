package com.example.practica_http.service.impl;

import com.example.practica_http.model.Producto;
import com.example.practica_http.service.ProductoService;

import java.util.List;
import java.util.Arrays;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "notebook", "computacion", 17500.0),
                new Producto(2L, "mesa escritorio", "oficina", 10000.0),
                new Producto(3L, "teclado mecanico", "computacion", 4.00));
    }
}


