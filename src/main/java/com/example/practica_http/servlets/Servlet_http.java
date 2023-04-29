package com.example.practica_http.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Servlet_http", value = "/Servlet_http")
public class Servlet_http extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String metodoHttp = req.getMethod();
        //Me da una cadena que me representa que tipo de método http se está utilizando, lo podría utilizar en el proyecto
        //cuando necesite saber cómo administrar un tipo de solicitud, o filtrar las solicitudes que me lleguen.
        String requestUri = req.getRequestURI();
        //este método me muestra la línea que va después de la url, se puede utilizar para saber que tipo de recurso me est
        //solicitando el cliente en la página.
        String requestUrl = req.getRequestURL().toString();
        //este método me sirve para obtener la URL donde está la información del puerto, el nombre del servidor, el protocolo,
        //también la URI, lo puedo usar en el proyecto para tener la información completa del HTTP, saber más detalladamente
        //la solicitud.
        String contexPath = req.getContextPath();
        //Sirve para obtener la ruta contexto de la página, la podría utilizar muy específicamente si solo necesito esa parte
        // de la ruta.
        String servletPath = req.getServletPath();
        //Me dice que server se está utilizando para manejar el HTTP, este lo puedo usar en el proyecto para saber que servlet
        // está operando en el http y así poder manejar esa parte mucho más fácil.
        String ipCliente = req.getRemoteAddr();
        //este se utiliza para saber la ip del cliente que hizo una solicitud en el HTTP, puedo utilizarla para restringir
        //esa ip y que no acceda a diferentes partes de la página o darle acceso solo a algunas ip.
        String ip = req.getLocalAddr();
        //Este método es para saber la ip del servidor que está ejecutando la página.
        int port = req.getLocalPort();
        //Este se utiliza para saber el Puerto local dónde se está ejecutando el servidor o más bien el número del puerto.
        //Se puede utilizar para dar reglas de enrutamiento en el servidor
        String scheme = req.getScheme();
        //Se utiliza para saber que protocolo que se está usando en la comunicación de servidor cliente, cómo saber que
        //si la conexión es segura o no. La puedo emplear para restringir conexiones no seguras y que solo accedan desde conexiones
        //seguras.
        String host = req.getHeader("host");
        //Se usa para saber el valor de los cabeceros del HTTP,
        String url = scheme + "://" + host + contexPath + servletPath;
        //
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;
        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
