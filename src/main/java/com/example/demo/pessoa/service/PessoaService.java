package com.example.demo.pessoa.service;

import com.example.demo.pessoa.entity.Pessoa;
import com.example.demo.pessoa.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getPessoa() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        logger.info("Número de pessoas recuperadas: {}", pessoas.size());
        return pessoas;
    }

    public void addNewPessoa(Pessoa pessoa) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findPessoaByDocumento(pessoa.getDocumento());
        if (optionalPessoa.isPresent()) {
            logger.error("Tentativa de adicionar pessoa com documento já cadastrado: {}", pessoa.getDocumento());
            throw new IllegalStateException("Documento já cadastrado");
        }
        pessoa.updateTipo(pessoa.getDocumento());
        pessoaRepository.save(pessoa);
        logger.info("Nova pessoa adicionada com sucesso: {}", pessoa);
    }

    public void deletePessoa(Long pessoaId) {
        boolean exists = pessoaRepository.existsById(pessoaId);
        if (!exists) {
            logger.error("Tentativa de deletar pessoa que não existe com ID: {}", pessoaId);
            throw new IllegalStateException("Pessoa não existe");
        } else {
            pessoaRepository.deleteById(pessoaId);
            logger.info("Pessoa com ID {} deletada com sucesso", pessoaId);
        }
    }

    @Transactional
    public void updateInfoPessoa(Long pessoaId, String name, String documento) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> {
            logger.error("Pessoa com ID {} não encontrada para atualização", pessoaId);
            return new IllegalStateException("Pessoa não encontrada");
        });

        boolean updated = false;

        if (name != null && !name.equals(pessoa.getNome())) {
            pessoa.setNome(name);
            updated = true;
            logger.info("Nome atualizado para a pessoa com ID {}: {}", pessoaId, name);
        }
        if (documento != null && !documento.equals(pessoa.getDocumento())) {
            pessoa.setDocumento(documento);
            updated = true;
            logger.info("Documento atualizado para a pessoa com ID {}: {}", pessoaId, documento);
        }

        if (updated) {
            logger.info("Informações da pessoa com ID {} atualizadas com sucesso", pessoaId);
        } else {
            logger.info("Nenhuma atualização realizada para a pessoa com ID {}", pessoaId);
        }
    }

    public Pessoa getPessoaById(long id) {
        Pessoa pessoa = pessoaRepository.findPessoasById(id);
        if (pessoa == null) {
            logger.warn("Pessoa com ID {} não encontrada", id);
        } else {
            logger.info("Pessoa recuperada com ID {}: {}", id, pessoa);
        }
        return pessoa;
    }
}
