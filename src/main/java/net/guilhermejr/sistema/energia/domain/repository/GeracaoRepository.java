package net.guilhermejr.sistema.energia.domain.repository;

import net.guilhermejr.sistema.energia.domain.entity.Geracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GeracaoRepository extends JpaRepository<Geracao, LocalDate> {

    @Query(value = "SELECT TO_CHAR(data, 'DD/MM'), gerado FROM geracao WHERE data BETWEEN (CURRENT_DATE - 30) AND (CURRENT_DATE - 1) ORDER BY data DESC", nativeQuery = true)
    List<Object[]> geracaoUltimos30Dias();

    @Query(value = "SELECT TO_CHAR(data, 'DD/MM'), gerado FROM geracao WHERE data BETWEEN (DATE_TRUNC('DAY', NOW()) - INTERVAL '12 MONTH + 30 DAY')::DATE AND (DATE_TRUNC('DAY', NOW()) - INTERVAL '12 MONTH + 1 DAY')::DATE ORDER BY data DESC", nativeQuery = true)
    List<Object[]> geracaoUltimos30DiasAnoPassado();

    @Query(value = "SELECT TO_CHAR(data, 'MM/YYYY') AS mes, SUM(gerado) AS gerado, TO_CHAR(data, 'YYYY-MM') AS ordenar FROM geracao WHERE data BETWEEN DATE_TRUNC('MONTH', NOW() - INTERVAL '11 MONTH')::DATE AND (DATE_TRUNC('MONTH', NOW()) + INTERVAL '1 MONTH - 1 DAY')::DATE GROUP BY mes, ordenar ORDER BY ordenar DESC", nativeQuery = true)
    List<Object[]> geracaoUltimos12Meses();

    @Query(value = "SELECT TO_CHAR(data, 'MM/YYYY') AS mes, SUM(gerado) AS gerado, TO_CHAR(data, 'YYYY-MM') AS ordenar FROM geracao WHERE data BETWEEN DATE_TRUNC('MONTH', NOW() - INTERVAL '23 MONTH')::DATE AND (DATE_TRUNC('MONTH', NOW()) - INTERVAL '11 MONTH + 1 DAY')::DATE GROUP BY mes, ordenar ORDER BY ordenar DESC", nativeQuery = true)
    List<Object[]> geracaoUltimos13a24Meses();

}
