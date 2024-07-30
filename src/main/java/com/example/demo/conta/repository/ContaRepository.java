package com.example.demo.conta.repository;
import com.example.demo.conta.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT c FROM Conta c WHERE c.id = ?1")
    Optional<Conta> findContaById(Long id);

}

