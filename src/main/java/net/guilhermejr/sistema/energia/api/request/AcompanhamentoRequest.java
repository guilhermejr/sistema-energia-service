package net.guilhermejr.sistema.energia.api.request;

import lombok.*;
import net.guilhermejr.sistema.energia.api.request.validation.constraints.DataBrasil;
import net.guilhermejr.sistema.energia.api.request.validation.constraints.ValorMonetario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcompanhamentoRequest {

    @NotBlank
    @DataBrasil
    private String inicio;

    @NotBlank
    @DataBrasil
    private String fim;

    @NotNull
    @PositiveOrZero
    private Integer energiaInjetada;

    @NotNull
    @Positive
    private Integer energiaConsumidaConcessionaria;

    @NotBlank
    @ValorMonetario
    private String tusd;

    @NotBlank
    @ValorMonetario
    private String te;

    @NotBlank
    @ValorMonetario
    private String bandeira;

    @NotBlank
    @ValorMonetario
    private String iluminacaoPublica;

    @NotBlank
    @ValorMonetario
    private String desconto;

    @NotBlank
    @ValorMonetario
    private String multa;

    @NotBlank
    @ValorMonetario
    private String juros;

}
