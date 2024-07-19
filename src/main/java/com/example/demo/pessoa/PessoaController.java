package com.example.demo.pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa addNewPessoa(@RequestBody Pessoa pessoa, @RequestParam String agencia) {
        return pessoaService.addNewPessoa(pessoa, agencia);
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
    public void updateInfoPessoa(@PathVariable("pessoaId") Long pessoaId, @RequestBody(required = false) String name, @RequestBody(required = false) String documento){
        pessoaService.updateInfoPessoa(pessoaId, name, documento);
    }

}