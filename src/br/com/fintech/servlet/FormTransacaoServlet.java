package br.com.fintech.servlet;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.model.Categoria;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
public class FormTransacaoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO categoriaDAO = DAOFactory.criarCategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listar();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("form-transacao.jsp").forward(request, response);
    }
}