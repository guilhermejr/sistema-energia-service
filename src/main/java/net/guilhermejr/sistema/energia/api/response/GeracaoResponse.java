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
public class GeracaoResponse {

    private LocalDate data;
    private BigDecimal gerado;
    private LocalDateTime criado;
    private LocalDateTime atualizado;

}
