package br.com.fintech.servlet;
import javax.servlet.http.HttpSession;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO dao = DAOFactory.criarUsuarioDAO();
        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.getNome());
            session.setAttribute("id_usuario", usuario.getId());
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("erro", "E-mail ou senha inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}