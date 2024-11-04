package net.guilhermejr.sistema.energia.api.mapper;

import net.guilhermejr.sistema.energia.api.response.GeracaoResponse;
import net.guilhermejr.sistema.energia.config.ModelMapperConfig;
import net.guilhermejr.sistema.energia.domain.entity.Geracao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeracaoMapper extends ModelMapperConfig {

    public List<GeracaoResponse> mapList(List<Geracao> geracoes) {
        return this.mapList(geracoes, GeracaoResponse.class);
    }

}
