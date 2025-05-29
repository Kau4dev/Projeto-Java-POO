package services;

import dao.CategoriaDAO;

public class CategoriaService {

    public void listarCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.listarCategorias();
    }
}
