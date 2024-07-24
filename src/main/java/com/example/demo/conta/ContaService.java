package com.example.demo.conta;

import com.example.demo.cartao.CartaoCredito;
import com.example.demo.cartao.CartaoCreditoRepository;
import com.example.demo.cheque.ChequeEspecial;
import com.example.demo.cheque.ChequeEspecialRepository;
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
        Conta conta = new Conta(agencia);
        conta.setDocumento(pessoa.getDocumento());
        conta.setTipoConta(pessoa.getTipo());

        contaRepository.save(conta);
        /*
        if (pessoa.getScore()>=2){
            CartaoCredito cartaoCredito = new CartaoCredito();
            ChequeEspecial chequeEspecial = new ChequeEspecial();

            cartaoCredito.updateLimiteCartaoCredito(pessoa.getScore());
            chequeEspecial.updateLimiteCheque(pessoa.getScore());

            conta.setLimites(chequeEspecial, cartaoCredito);

            cartaoCreditoRepository.save(cartaoCredito);
            chequeEspecialRepository.save(chequeEspecial);
        }*/

        if (pessoa.getScore() >= 2 && pessoa.getScore() <= 5) {
            chequeEspecialRepository.save(new ChequeEspecial(1000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(200.0, conta));
        } else if (pessoa.getScore() >= 6 && pessoa.getScore() <= 8) {
            chequeEspecialRepository.save(new ChequeEspecial(2000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(2000.0, conta));
        } else if (pessoa.getScore() == 9) {
            chequeEspecialRepository.save(new ChequeEspecial(5000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(15000.0, conta));
        }/*

/*        if (pessoa.getScore()>=2){
            CartaoCredito cartaoCredito = new CartaoCredito();
            ChequeEspecial chequeEspecial = new ChequeEspecial();

            cartaoCredito.updateLimiteCartaoCredito(pessoa.getScore());
            chequeEspecial.updateLimiteCheque(pessoa.getScore());

            conta.setLimites(chequeEspecial, cartaoCredito);

            cartaoCreditoRepository.save(cartaoCredito);
            chequeEspecialRepository.save(chequeEspecial);
        }*/
        System.out.println("Hallo!");
        System.out.println(conta.toString());


    }

    public List<Conta> getContas() {
        return contaRepository.findAll();
    }

    public void deletePessoa(Long pessoaId) {
    }
}
