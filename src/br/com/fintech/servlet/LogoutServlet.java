package br.com.fintech.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
            sessao.invalidate(); // Encerra a sessão
        }
        response.sendRedirect("login.jsp"); // Redireciona para login
    }
}