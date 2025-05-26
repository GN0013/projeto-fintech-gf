package br.com.fintech.servlet;

import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CadastroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO dao = DAOFactory.criarUsuarioDAO();
        System.out.println("tentando cadastrar"+ email);
        if (dao.buscarPorEmail(email) != null) {
            request.setAttribute("erro", "Usuário já cadastrado");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        } else {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            dao.inserir(usuario);
            response.sendRedirect("login.jsp");

        }
    }
}