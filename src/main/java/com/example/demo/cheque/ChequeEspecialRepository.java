package com.example.demo.cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeEspecialRepository extends JpaRepository<ChequeEspecial, Long> {
}
