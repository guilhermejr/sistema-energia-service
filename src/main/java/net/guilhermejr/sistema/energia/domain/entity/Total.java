package net.guilhermejr.sistema.energia.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "totais")
public class Total implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal energiaGerada;

    @Column(nullable = false)
    private Integer energiaInjetada;

    @Column(nullable = false)
    private Integer energiaConsumidaConcessionaria;

    @Column(nullable = false)
    private BigDecimal energiaConsumidaTotal;

    @Column(nullable = false)
    private Integer saldoMes;

    @Column(nullable = false)
    private BigDecimal tusd;

    @Column(nullable = false)
    private BigDecimal tusdReal;

    @Column(nullable = false)
    private BigDecimal te;

    @Column(nullable = false)
    private BigDecimal teReal;

    @Column(nullable = false)
    private BigDecimal bandeira;

    @Column(nullable = false)
    private BigDecimal iluminacaoPublica;

    @Column(nullable = false)
    private BigDecimal iluminacaoPublicaReal;

    @Column(nullable = false)
    private BigDecimal desconto;

    @Column(nullable = false)
    private BigDecimal multa;

    @Column(nullable = false)
    private BigDecimal multaReal;

    @Column(nullable = false)
    private BigDecimal juros;

    @Column(nullable = false)
    private BigDecimal jurosReal;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal valorTotalReal;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime atualizado;

}
