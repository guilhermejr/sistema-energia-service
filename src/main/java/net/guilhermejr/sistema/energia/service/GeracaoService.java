package net.guilhermejr.sistema.energia.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.response.RetornoPadraoGraficoResponse;
import net.guilhermejr.sistema.energia.domain.repository.GeracaoRepository;
import net.guilhermejr.sistema.energia.util.ProcessaDadosGraficoUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@AllArgsConstructor
@Service
public class GeracaoService {

    private final GeracaoRepository geracaoRepository;
    private final ProcessaDadosGraficoUtil processaDadosGraficoUtil;

    // --- geracaoUltimos30Dias -----------------------------------------------
    public List<RetornoPadraoGraficoResponse> geracaoUltimos30Dias() {

        List<Object[]> dados = geracaoRepository.geracaoUltimos30Dias();
        return processaDadosGraficoUtil.processar(dados);

    }

    // --- geracaoUltimos30DiasAnoPassado -------------------------------------
    public List<RetornoPadraoGraficoResponse> geracaoUltimos30DiasAnoPassado() {

        List<Object[]> dados = geracaoRepository.geracaoUltimos30DiasAnoPassado();
        return processaDadosGraficoUtil.processar(dados);

    }

    // --- geracaoUltimos12meses ----------------------------------------------
    public List<RetornoPadraoGraficoResponse> geracaoUltimos12Meses()  {

        List<Object[]> dados = geracaoRepository.geracaoUltimos12Meses();
        return processaDadosGraficoUtil.processar(dados);

    }

    // --- geracaoUltimos13a24Meses -------------------------------------------
    public List<RetornoPadraoGraficoResponse> geracaoUltimos13a24Meses()  {

        List<Object[]> dados = geracaoRepository.geracaoUltimos13a24Meses();
        return processaDadosGraficoUtil.processar(dados);

    }

}
