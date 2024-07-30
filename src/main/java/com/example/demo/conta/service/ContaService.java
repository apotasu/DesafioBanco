package com.example.demo.conta.service;

import com.example.demo.cartao.entity.CartaoCredito;
import com.example.demo.cartao.repository.CartaoCreditoRepository;
import com.example.demo.cheque.entity.ChequeEspecial;
import com.example.demo.cheque.repository.ChequeEspecialRepository;
import com.example.demo.conta.entity.Conta;
import com.example.demo.conta.repository.ContaRepository;
import com.example.demo.pessoa.entity.Pessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private ChequeEspecialRepository chequeEspecialRepository;

    public void createAccount(Pessoa pessoa, String agencia) {
        logger.info("Criando conta para a pessoa: {} com agência: {}", pessoa.getNome(), agencia);
        Conta conta = new Conta(agencia, pessoa.getDocumento(), pessoa);
        conta.setTipoConta(pessoa.getTipo());
        contaRepository.save(conta);
        logger.info("Conta criada com sucesso: {}", conta);

        if (pessoa.getScore() >= 2 && pessoa.getScore() <= 5) {
            chequeEspecialRepository.save(new ChequeEspecial(1000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(200.0, conta));
            logger.info("Cheque especial e cartão de crédito adicionados para pessoa com score entre 2 e 5");
        } else if (pessoa.getScore() >= 6 && pessoa.getScore() <= 8) {
            chequeEspecialRepository.save(new ChequeEspecial(2000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(2000.0, conta));
            logger.info("Cheque especial e cartão de crédito adicionados para pessoa com score entre 6 e 8");
        } else if (pessoa.getScore() == 9) {
            chequeEspecialRepository.save(new ChequeEspecial(5000.0, conta));
            cartaoCreditoRepository.save(new CartaoCredito(15000.0, conta));
            logger.info("Cheque especial e cartão de crédito adicionados para pessoa com score igual a 9");
        } else {
            logger.warn("Score da pessoa fora dos limites esperados: {}", pessoa.getScore());
        }
    }

    public void deleteAccount(Long contaId) {
        if (contaRepository.findById(contaId).isPresent()) {
            contaRepository.deleteById(contaId);
            logger.info("Conta com ID {} deletada com sucesso", contaId);
        } else {
            logger.warn("Conta com ID {} não encontrada para deleção", contaId);
        }
    }

    public List<Conta> getContas() {
        List<Conta> contas = contaRepository.findAll();
        logger.info("Número de contas recuperadas: {}", contas.size());
        return contas;
    }
}
