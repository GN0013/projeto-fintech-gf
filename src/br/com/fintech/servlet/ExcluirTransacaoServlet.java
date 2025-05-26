package br.com.fintech.servlet;

import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.TransacaoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class ExcluirTransacaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idTransacao = Integer.parseInt(request.getParameter("id_transacao"));
            TransacaoDAO dao = DAOFactory.criarTransacaoDAO();
            dao.excluir(idTransacao);

            response.sendRedirect("listar-transacoes");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao excluir transação");
            request.getRequestDispatcher("listar-transacoes.jsp").forward(request, response);
        }
    }
}