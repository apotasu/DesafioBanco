package com.example.demo.pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public void addNewPessoa(@RequestBody Pessoa pessoa) {
        pessoaService.addNewPessoa(pessoa);
    }

    @GetMapping
    public List<Pessoa> getPessoa(){
        return pessoaService.getPessoa();
    }
    @DeleteMapping(path = "{pessoaId}")
    public void deleteAnPessoa(@PathVariable("pessoaId") Long pessoaId){
        pessoaService.deletePessoa(pessoaId);
    }

    //Put -> Patch
    //@RequestParam -> RequestBody
    @PatchMapping(path = "{pessoaId}")
    public void updateInfoPessoa(@PathVariable("pessoaId") Long pessoaId, @RequestBody Pessoa pessoa){
        pessoaService.updateInfoPessoa(pessoaId, pessoa.getNome(), pessoa.getDocumento());
    }

}