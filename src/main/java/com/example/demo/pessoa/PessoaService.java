package com.example.demo.pessoa;


import com.example.demo.conta.Conta;
import com.example.demo.conta.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    public Pessoa addNewPessoa(Pessoa pessoa, String agencia) {
        Optional<Pessoa> pessoaByDocumento = pessoaRepository.findPessoaByDocumento(pessoa.getDocumento());
        if (pessoaByDocumento.isPresent()){
            throw new IllegalStateException("Documento já cadastrado");
        }
        Pessoa novaPessoa = pessoaRepository.save(pessoa);
        Conta conta = new Conta(novaPessoa, agencia);
        contaRepository.save(conta);
        System.out.println(pessoa);
        return novaPessoa;
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
        if (documento.length()==11 || documento.length()==14) {
            pessoa.setDocumento(documento);
        }
        System.out.println(documento);
    }
}
