package net.guilhermejr.sistema.energia.api.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeradoResponse {

    BigDecimal gerado;

}
