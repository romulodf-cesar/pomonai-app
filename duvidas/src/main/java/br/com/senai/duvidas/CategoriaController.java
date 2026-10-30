package br.com.senai.duvidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;
import br.com.senai.duvidas.Categoria;
@Controller 
public class CategoriaController {
    @Autowired
    private CategoriaRepository repository; 
  
    @PostMapping("/categoria/cadastrar")
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroCategoria dados){      
        repository.save(new Categoria(dados));
    } 

    @GetMapping("/categoria")
    public String faq(Model model){
        return "faq";
    }
}
