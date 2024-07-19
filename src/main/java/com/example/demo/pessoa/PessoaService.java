package com.example.demo.pessoa;


import com.example.demo.cartao.CartaoCredito;
import com.example.demo.cartao.CartaoCreditoRepository;
import com.example.demo.cheque.ChequeEspecial;
import com.example.demo.cheque.ChequeEspecialRepository;
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

    @Autowired
    private ChequeEspecialRepository chequeEspecialRepository;

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    public Pessoa addNewPessoa(Pessoa pessoa, String agencia) {
            Optional<Pessoa> optionalPessoa = pessoaRepository.findPessoaByDocumento(pessoa.getDocumento());
            if(optionalPessoa.isPresent()){
                throw new IllegalStateException("Documento já cadastrado");
            }
            Conta conta = new Conta(agencia);
            conta.setTipoConta(pessoa.getTipo());
            conta.setPessoa(pessoa);
            pessoaRepository.save(pessoa);
            contaRepository.save(conta);

            if (pessoa.getScore() >= 2 && pessoa.getScore() <= 5) {
                chequeEspecialRepository.save(new ChequeEspecial(1000.0, conta));
                cartaoCreditoRepository.save(new CartaoCredito(200.0, conta));

            } else if (pessoa.getScore() >= 6 && pessoa.getScore() <= 8) {
                chequeEspecialRepository.save(new ChequeEspecial(2000.0, conta));
                cartaoCreditoRepository.save(new CartaoCredito(2000.0, conta));

            } else if (pessoa.getScore() == 9) {
                chequeEspecialRepository.save(new ChequeEspecial(5000.0, conta));
                cartaoCreditoRepository.save(new CartaoCredito(15000.0, conta));
            }

            return pessoa;
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
