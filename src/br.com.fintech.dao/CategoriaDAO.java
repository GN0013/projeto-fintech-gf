package br.com.fintech.dao;

import br.com.fintech.model.Categoria;
import java.util.List;

public interface CategoriaDAO {
    List<Categoria> listar();
}