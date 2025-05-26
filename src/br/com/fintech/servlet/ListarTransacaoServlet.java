package br.com.fintech.servlet;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.model.Transacao;
import oracle.jdbc.internal.XSCacheOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
public class ListarTransacaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer idUsuario = (session != null) ? (Integer) session.getAttribute("id_usuario") : null;
        System.out.println("id do usuario" + idUsuario);
        if (idUsuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            TransacaoDAO dao = DAOFactory.criarTransacaoDAO();
            List<Transacao> transacoes = dao.listarPorUsuario(idUsuario);

            request.setAttribute("transacoes", transacoes);
            request.getRequestDispatcher("listar-transacoes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao listar transações.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}