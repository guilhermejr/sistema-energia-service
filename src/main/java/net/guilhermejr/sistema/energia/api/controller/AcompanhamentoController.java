package net.guilhermejr.sistema.energia.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.request.AcompanhamentoRequest;
import net.guilhermejr.sistema.energia.api.response.*;
import net.guilhermejr.sistema.energia.service.AcompanhamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@PreAuthorize("hasAnyRole('ENERGIA')")
@RequestMapping("/acompanhamentos")
public class AcompanhamentoController {

    private final AcompanhamentoService acompanhamentoService;

    // --- Retornar -----------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<AcompanhamentoResponse>> retornar() {

        log.info("Retornando acompanhamentos");
        List<AcompanhamentoResponse> acompanhamentoResponseList = acompanhamentoService.retornar();
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoResponseList);

    }

    // --- RetornarUm ---------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<AcompanhamentoResponse> retornarUm(@PathVariable Long id) {

        log.info("Recuperando um acompanhamento: {}", id);
        AcompanhamentoResponse acompanhamentoResponse = acompanhamentoService.retornarUm(id);
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoResponse);

    }

    // --- Inserir ------------------------------------------------------------
    @PostMapping
    public ResponseEntity<AcompanhamentoResponse> inserir(@Valid @RequestBody AcompanhamentoRequest acompanhamentoRequest) {

        log.info("Inserindo acompanhamento");
        AcompanhamentoResponse acompanhamentoResponse = acompanhamentoService.inserir(acompanhamentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamentoResponse);

    }

    // --- Atualizar ----------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<AcompanhamentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AcompanhamentoRequest acompanhamentoRequest) {

        log.info("Atualizando acompanhamento: {}", id);
        AcompanhamentoResponse acompanhamentoResponse = acompanhamentoService.atualizar(id, acompanhamentoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoResponse);

    }

    // --- Apagar -------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {

        log.info("Apagando acompanhamento: {}", id);
        acompanhamentoService.apagar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    // --- consumoUltimos12Meses ----------------------------------------------
    @GetMapping("/consumoultimos12meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> consumoUltimos12Meses() {

        log.info("Retornando consumoUltimos12Meses");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.consumoUltimos12Meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- saldoUltimos12Meses ------------------------------------------------
    @GetMapping("/saldoultimos12meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> saldoUltimos12Meses() {

        log.info("Retornando saldoUltimos12Meses");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.saldoUltimos12Meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracaoUltimos30dias -----------------------------------------------
    @GetMapping("/geracaoultimos30dias")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos30dias() {

        log.info("Retornando geracaoUltimos30dias");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.geracaoUltimos30dias();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracaoUltimos12meses ----------------------------------------------
    @GetMapping("/geracaoultimos12meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos12meses() {

        log.info("Retornando geracaoUltimos12meses");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.geracaoUltimos12meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- contaUltimoMes -----------------------------------------------------
    @GetMapping("/contaultimomes")
    public ResponseEntity<AcompanhamentoResponse> contaUltimoMes() {

        log.info("Retornando contaUltimoMes");
        AcompanhamentoResponse resultado = acompanhamentoService.contaUltimoMes();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geradoDesdeUltimaLeitura -------------------------------------------
    @GetMapping("/geradosdesdeultimaleitura")
    public ResponseEntity<GeracaoResponse> geradoDesdeUltimaLeitura() {

        log.info("Retornando geradoDesdeUltimaLeitura");
        GeracaoResponse resultado = acompanhamentoService.geradoDesdeUltimaLeitura();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- valorUltimos12Meses ------------------------------------------------
    @GetMapping("/valorultimos12meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> valorUltimos12Meses() {

        log.info("Retornando valorUltimos12Meses");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.valorUltimos12Meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracao12MesesAnteriores -------------------------------------------
    @GetMapping("/geracao12mesesanteriores")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracao12MesesAnteriores() {

        log.info("Retornando geracao12MesesAnteriores");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.geracao12MesesAnteriores();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- consumo12MesesAnteriores -------------------------------------------
    @GetMapping("/consumo12mesesanteriores")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> consumo12MesesAnteriores() {

        log.info("Retornando consumo12MesesAnteriores");
        List<RetornoPadraoGraficoResponse> resultado = acompanhamentoService.consumo12MesesAnteriores();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

}
