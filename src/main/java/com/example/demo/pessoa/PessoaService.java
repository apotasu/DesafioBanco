package com.example.demo.pessoa;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    public void addNewPessoa(Pessoa pessoa) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findPessoaByDocumento(pessoa.getDocumento());
        if(optionalPessoa.isPresent()){
            throw new IllegalStateException("Documento já cadastrado");
        }
        pessoa.updateTipo(pessoa.getDocumento());
        pessoaRepository.save(pessoa);

        }

    public void deletePessoa(Long pessoaId) {
        boolean exists = pessoaRepository.existsById(pessoaId);
        if (!exists){
            throw new IllegalStateException("Pessoa não existe");
        } else {
            pessoaRepository.deleteById(pessoaId);
        }
    }


    @Transactional
    public void updateInfoPessoa(Long pessoaId, String name, String documento) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();
        if (name!=null) {
            pessoa.setNome(name);
        }
        if (documento!=null) {
            pessoa.setDocumento(documento);
        }
        System.out.println(name);
        System.out.println(documento);
    }
}
