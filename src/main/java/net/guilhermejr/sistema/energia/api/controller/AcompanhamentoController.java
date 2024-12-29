package net.guilhermejr.sistema.energia.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.request.AcompanhamentoRequest;
import net.guilhermejr.sistema.energia.api.response.AcompanhamentoResponse;
import net.guilhermejr.sistema.energia.api.response.RetornoPadraoGraficoResponse;
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

}
