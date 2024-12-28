package net.guilhermejr.sistema.energia.api.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcompanhamentoResponse {

    private Long id;
    private LocalDate inicio;
    private LocalDate fim;
    private Integer dias;
    private BigDecimal energiaGerada;
    private Integer energiaInjetada;
    private Integer energiaConsumidaConcessionaria;
    private BigDecimal energiaConsumidaTotal;
    private Integer saldoMes;
    private Integer saldoMesAcumulado;
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
    private LocalDateTime criado;
    private LocalDateTime atualizado;

}
