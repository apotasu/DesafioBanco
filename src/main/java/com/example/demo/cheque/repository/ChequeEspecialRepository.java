package com.example.demo.cheque.repository;
import com.example.demo.cheque.entity.ChequeEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeEspecialRepository extends JpaRepository<ChequeEspecial, Long> {
}
