package br.com.senai.duvidas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //dados
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class FaqController {

    private DuvidaRepository repository;

    public FaqController(DuvidaRepository repository){
        this.repository = repository;
    }
    //enviar os dados para o HTML

    
    @GetMapping("/faq")
    public String faq(Model model){
        model.addAttribute("duvidas", repository.findAllByOrderByDataHoraDesc());
        return "faq";
    }

}
