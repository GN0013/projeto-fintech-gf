package br.com.fintech.servlet;

import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.TransacaoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class SaldoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession(false);
        Integer idUsuario = (sessao != null) ? (Integer) sessao.getAttribute("id_usuario") : null;

        if (idUsuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        TransacaoDAO dao = DAOFactory.criarTransacaoDAO();
        double totalEntrada = dao.calcularTotalPorTipo(idUsuario, "entrada");
        double totalSaida = dao.calcularTotalPorTipo(idUsuario, "saida");
        double saldo = totalEntrada - totalSaida;

        request.setAttribute("entrada", totalEntrada);
        request.setAttribute("saida", totalSaida);
        request.setAttribute("saldo", saldo);

        request.getRequestDispatcher("saldo.jsp").forward(request, response);
    }
}