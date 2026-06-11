package net.guilhermejr.sistema.energia.api.mapper;

import net.guilhermejr.sistema.energia.api.response.GeracaoResponse;
import net.guilhermejr.sistema.energia.config.ModelMapperConfig;
import net.guilhermejr.sistema.energia.domain.entity.Geracao;
import org.springframework.stereotype.Component;

@Component
public class GeracaoMapper extends ModelMapperConfig {

    public GeracaoResponse mapObject(Geracao geracao) {
        return this.mapObject(geracao, GeracaoResponse.class);
    }

}
