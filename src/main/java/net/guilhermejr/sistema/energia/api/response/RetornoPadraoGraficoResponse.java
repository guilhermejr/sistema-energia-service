package net.guilhermejr.sistema.energia.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetornoPadraoGraficoResponse {

    private String periodo;
    private BigDecimal valor;
    private BigDecimal valor2;

}
