package com.example.demo.conta.service;

import com.example.demo.cartao.entity.CartaoCredito;
import com.example.demo.cartao.repository.CartaoCreditoRepository;
import com.example.demo.cheque.entity.ChequeEspecial;
import com.example.demo.cheque.repository.ChequeEspecialRepository;
import com.example.demo.conta.entity.Conta;
import com.example.demo.conta.repository.ContaRepository;
import com.example.demo.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;
    @Autowired
    private ChequeEspecialRepository chequeEspecialRepository;

    public void createAccount(Pessoa pessoa, String agencia) {
        Conta conta = new Conta(agencia, pessoa.getDocumento(), pessoa);
        conta.setTipoConta(pessoa.getTipo());
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

    }

    public void deletePessoa(Long pessoaId) {
    }

    public List<Conta> getContas() {
        return contaRepository.findAll();
    }
}
