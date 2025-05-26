package br.com.fintech.dao;

public class DAOFactory {

    public static UsuarioDAO criarUsuarioDAO() {
        return new UsuarioDAOImpl();
    }

    public static TransacaoDAO criarTransacaoDAO() {
        return new TransacaoDAOImpl();
    }

    public static CategoriaDAO criarCategoriaDAO() {return new CategoriaDAOImpl();}

}