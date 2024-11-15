package net.guilhermejr.sistema.energia.util;

import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.response.RetornoPadraoGraficoResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class ProcessaDadosGraficoUtil {

    public List<RetornoPadraoGraficoResponse> processar(List<Object[]> dados)  {

        List<RetornoPadraoGraficoResponse> retorno = new ArrayList<>();
        dados.forEach(res -> {
            RetornoPadraoGraficoResponse retornoPadraoGraficoResponse = new RetornoPadraoGraficoResponse();
            retornoPadraoGraficoResponse.setPeriodo(res[0].toString());
            retornoPadraoGraficoResponse.setValor(new BigDecimal(String.valueOf(res[1])));
            retorno.add(retornoPadraoGraficoResponse);
        });
        return retorno;

    }

}
