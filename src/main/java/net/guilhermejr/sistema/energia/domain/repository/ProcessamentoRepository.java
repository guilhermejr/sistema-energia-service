package net.guilhermejr.sistema.energia.domain.repository;

import net.guilhermejr.sistema.energia.domain.entity.Processamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessamentoRepository  extends JpaRepository<Processamento, Long> {
}
