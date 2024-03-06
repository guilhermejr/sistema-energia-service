package net.guilhermejr.sistema.energia.domain.repository;

import net.guilhermejr.sistema.energia.domain.entity.Acompanhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long> {

    List<Acompanhamento> findAllByOrderByInicioAsc();

    @Query(value = "SELECT SUM(gerado) FROM geracao WHERE data BETWEEN ?1 AND ?2", nativeQuery = true)
    BigDecimal energiaGerada(LocalDate inicio, LocalDate fim);

}