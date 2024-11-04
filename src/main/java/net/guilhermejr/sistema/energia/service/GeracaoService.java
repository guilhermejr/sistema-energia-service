package net.guilhermejr.sistema.energia.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.mapper.GeracaoMapper;
import net.guilhermejr.sistema.energia.api.response.GeracaoResponse;
import net.guilhermejr.sistema.energia.domain.entity.Geracao;
import net.guilhermejr.sistema.energia.domain.repository.GeracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@AllArgsConstructor
@Service
public class GeracaoService {

    private GeracaoRepository geracaoRepository;
    private GeracaoMapper geracaoMapper;

    // --- Retornar -----------------------------------------------------------
    public List<GeracaoResponse> retornar() {

        List<Geracao> geracao = geracaoRepository.geracaoUltimos30Dias();
        return geracaoMapper.mapList(geracao);

    }

}
