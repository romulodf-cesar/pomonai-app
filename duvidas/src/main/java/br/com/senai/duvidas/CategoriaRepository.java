package br.com.senai.duvidas;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
    // crie um método para listar as categorias
    List<Categoria> findAll();
}
