package br.com.senai.duvidas;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DuvidaRepository extends JpaRepository<Duvida,Long> {

     // criar método para listar todos
     /* criar método abstrato para listar todas as duvidas.   */  
      List<Duvida> findAllByOrderByDataHoraDesc();
}


