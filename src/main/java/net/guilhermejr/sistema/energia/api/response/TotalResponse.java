package net.guilhermejr.sistema.energia.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TotalResponse {

    private Long id;
    private BigDecimal energiaGerada;
    private Integer energiaInjetada;
    private Integer energiaConsumidaConcessionaria;
    private BigDecimal energiaConsumidaTotal;
    private Integer saldoMes;
    private BigDecimal tusd;
    private BigDecimal tusdReal;
    private BigDecimal te;
    private BigDecimal teReal;
    private BigDecimal bandeira;
    private BigDecimal iluminacaoPublica;
    private BigDecimal iluminacaoPublicaReal;
    private BigDecimal desconto;
    private BigDecimal multa;
    private BigDecimal multaReal;
    private BigDecimal juros;
    private BigDecimal jurosReal;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalReal;
    private LocalDateTime atualizado;

}
