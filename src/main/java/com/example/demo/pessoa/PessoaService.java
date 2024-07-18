package com.example.demo.pessoa;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    public void addNewPessoa(Pessoa pessoa) {
        Optional<Pessoa> pessoaByDocumento = pessoaRepository.findPessoasByDocumento(pessoa.getDocumento());
        if (pessoaByDocumento.isPresent()){
            throw new IllegalStateException("Documento já cadastrado");
        }
        pessoaRepository.save(pessoa);
        System.out.println(pessoa);
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
        if (name!=null && name.length()>0) {
            pessoa.setNome(name);
        }
        if (documento!=null && documento.length()>0) {
            pessoa.setDocumento(documento);
        }
        System.out.println(documento);
    }
}
