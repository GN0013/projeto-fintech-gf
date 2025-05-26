package br.com.fintech.servlet;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.model.Transacao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
public class CadastrarTransacaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("id_usuario") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            double valor = Double.parseDouble(request.getParameter("valor"));
            String tipo = request.getParameter("tipo");
            int idUsuario = (int) session.getAttribute("id_usuario");
            int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));

            Transacao transacao = new Transacao();
            transacao.setValor(valor);
            transacao.setTipo(tipo);
            transacao.setData(new Date());
            transacao.setIdUsuario(idUsuario);
            transacao.setIdCategoria(idCategoria);

            TransacaoDAO dao = DAOFactory.criarTransacaoDAO();
            dao.inserir(transacao);

            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar transação");
            request.getRequestDispatcher("form-transacao.jsp").forward(request, response);
        }
    }
}