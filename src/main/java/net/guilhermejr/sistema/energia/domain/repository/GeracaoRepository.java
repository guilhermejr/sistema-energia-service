package net.guilhermejr.sistema.energia.domain.repository;

import net.guilhermejr.sistema.energia.domain.entity.Geracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GeracaoRepository extends JpaRepository<Geracao, LocalDate> {

    @Query(value = "SELECT data, gerado, criado, atualizado FROM geracao WHERE data BETWEEN (CURRENT_DATE - 30) AND (CURRENT_DATE - 1) ORDER BY data DESC", nativeQuery = true)
    List<Geracao> geracaoUltimos30Dias();

}
