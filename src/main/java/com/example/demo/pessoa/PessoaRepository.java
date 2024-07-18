package com.example.demo.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p WHERE p.documento = ?1")
    Optional<Pessoa> findPessoasByDocumento(String documento);

}
