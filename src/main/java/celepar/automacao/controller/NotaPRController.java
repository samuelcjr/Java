package celepar.automacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import celepar.automacao.dto.UrlEmailDTO;
import celepar.automacao.service.NotaPRService;

@RestController
@RequestMapping("/notapr")
public class NotaPRController {

    
    private NotaPRService notaPRService;

    @Autowired  
    public NotaPRController(NotaPRService notaPRService) {
        this.notaPRService = notaPRService;
    }

    @GetMapping("/usuario/cadastro/{cpf}")
    public String usuarioCadastro(@PathVariable String cpf) {
        return notaPRService.usuarioCadastro(cpf);
    }

    @PostMapping("/usuario/cadastro/passo02")
    public String usuarioCadastro02(@RequestBody UrlEmailDTO req) {
        return notaPRService.usuarioCadastro02(req.getUrlEmail());
    }

    //teste2 

}
