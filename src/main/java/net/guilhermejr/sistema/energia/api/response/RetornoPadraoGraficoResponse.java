package net.guilhermejr.sistema.energia.api.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetornoPadraoGraficoResponse {

    private String periodo;
    private BigDecimal valor;

}
