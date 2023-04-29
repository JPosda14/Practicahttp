package com.example.practica_http.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Producto {
    private long id;
    private String name;
    private String type;
    private Double price;
}
