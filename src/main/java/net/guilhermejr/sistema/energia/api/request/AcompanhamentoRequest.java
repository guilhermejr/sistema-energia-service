package net.guilhermejr.sistema.energia.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Request para acompanhamento")
public class AcompanhamentoRequest {

    @NotBlank
    @DataBrasil
    @Schema(description = "Data do início do ciclo de consumo", example = "18/11/2021")
    private String inicio;

    @NotBlank
    @DataBrasil
    @Schema(description = "Data do fim do ciclo de consumo", example = "20/12/2021")
    private String fim;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Energia injetada", example = "485")
    private Integer energiaInjetada;

    @NotNull
    @Positive
    @Schema(description = "Energia consumida da concessionária", example = "339")
    private Integer energiaConsumidaConcessionaria;

    @NotBlank
    @ValorMonetario
    @Schema(description = "TUSD", example = "29,04")
    private String tusd;

    @NotBlank
    @ValorMonetario
    @Schema(description = "TE", example = "16,56")
    private String te;

    @NotBlank
    @ValorMonetario
    @Schema(description = "Bandeira tarifária", example = "10,45")
    private String bandeira;

    @NotBlank
    @ValorMonetario
    @Schema(description = "Iluminação pública", example = "1,12")
    private String iluminacaoPublica;

    @NotBlank
    @ValorMonetario
    @Schema(description = "Desconto, caso haja", example = "0,00")
    private String desconto;

    @NotBlank
    @ValorMonetario
    @Schema(description = "Desconto, caso haja", example = "0,00")
    private String multa;

    @NotBlank
    @ValorMonetario
    @Schema(description = "Desconto, caso haja", example = "0,00")
    private String juros;

}
