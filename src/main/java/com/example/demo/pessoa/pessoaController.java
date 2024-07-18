package com.example.demo.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pessoa")
public class pessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public pessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> getStudent(){
        return pessoaService.getPessoa();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Pessoa pessoa){
        pessoaService.addNewPessoa(pessoa);
    }
    @DeleteMapping(path = "{pessoaId}")
    public void deleteAnPessoa(@PathVariable("pessoaId") Long pessoaId){
        pessoaService.deletePessoa(pessoaId);
    }
    @PutMapping(path = "{pessoaId}")
    public void updateInfoPessoa(@PathVariable("pessoaId") Long pessoaId, @RequestParam(required = false) String name, @RequestParam(required = false) String documento){
        pessoaService.updateInfoPessoa(pessoaId, name, documento);
    }
}
