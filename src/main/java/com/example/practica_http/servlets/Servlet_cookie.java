package com.example.practica_http.servlets;

import com.example.practica_http.model.Producto;
import com.example.practica_http.service.LoginService;
import com.example.practica_http.service.ProductoService;
import com.example.practica_http.service.impl.LoginServiceImpl;
import com.example.practica_http.service.impl.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static com.example.practica_http.servlets.Servlet_login.USERNAME;

@WebServlet(name = "Servlet_cookie", value = "/Servlet_cookie")
public class Servlet_cookie extends HttpServlet {
    final static String USER_NAME = "admin";
    final static String PASSWORD = "1448";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        ProductoService productService=new ProductoServiceImpl();
        List<Producto> products=productService.listar();
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + cookieOptional.get() +
                        "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + cookieOptional.get() + " has iniciado sesión con éxito!</h1>");
                out.println("       <table>");
                out.println("           <tr>");
                out.println("               <th>id</th>");
                out.println("               <th>nombre</th>");
                out.println("               <th>tipo</th>");
                out.println("               <th>precio</th>");
                out.println("           </tr>");
                products.forEach(p->{
                    out.println("           <tr>");
                    out.println("               <th>"+p.getId()+"</th>");
                    out.println("               <th>"+p.getName()+"</th>");
                    out.println("               <th>"+p.getType()+"</th>");
                    out.println("               <th>"+p.getPrice()+"</th>");
                    out.println("           </tr>");
                });
                out.println("       </table>");
                out.println("<p><a href='" + req.getContextPath() +
                        "'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/logincookies.jsp").forward(req,
                    resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.sendRedirect(req.getContextPath() + "/logincookies");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
