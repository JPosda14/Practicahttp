package com.example.practica_http.servlets;

import com.example.practica_http.model.Producto;
import com.example.practica_http.service.ProductoService;
import com.example.practica_http.service.impl.ProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products.json")
public class Servlet_json extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service =new ProductoServiceImpl();
        List<Producto> products = service.listar();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(products);
        resp.setContentType("application/json");
        resp.getWriter().write(json);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto products = mapper.readValue(jsonStream, Producto.class);
        resp.setContentType("txt/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Detalle de producto desde json</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: " + products.getId() + "</li>");
            out.println("<li>Nombre: " + products.getName() + "</li>");
            out.println("<li>Tipo: " + products.getType() + "</li>");
            out.println("<li>Precio: " + products.getPrice() + "</li>");
            out.println("</ul>");
            out.println("    </body>");
            out.println("</html>");

        }

    }
}
