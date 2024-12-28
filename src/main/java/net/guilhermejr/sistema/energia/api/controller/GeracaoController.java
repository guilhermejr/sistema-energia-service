package net.guilhermejr.sistema.energia.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.response.RetornoPadraoGraficoResponse;
import net.guilhermejr.sistema.energia.service.GeracaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/geracao")
public class GeracaoController {

    private final GeracaoService geracaoService;

    // --- geracaoUltimos30Dias -----------------------------------------------
    @GetMapping("/ultimos30dias")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos30Dias() {

        log.info("Retornando geracaoUltimos30Dias");
        List<RetornoPadraoGraficoResponse> resultado = geracaoService.geracaoUltimos30Dias();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracaoUltimos30DiasAnoPassado -------------------------------------
    @GetMapping("/ultimos30Diasanopassado")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos30DiasAnoPassado() {

        log.info("Retornando geracaoUltimos30DiasAnoPassado");
        List<RetornoPadraoGraficoResponse> resultado = geracaoService.geracaoUltimos30DiasAnoPassado();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracaoUltimos12Meses ----------------------------------------------
    @GetMapping("/ultimos12meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos12Meses() {

        log.info("Retornando geracaoUltimos12Meses");
        List<RetornoPadraoGraficoResponse> resultado = geracaoService.geracaoUltimos12Meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

    // --- geracaoUltimos13a24Meses -------------------------------------------
    @GetMapping("/ultimos13a24meses")
    public ResponseEntity<List<RetornoPadraoGraficoResponse>> geracaoUltimos13a24Meses() {

        log.info("Retornando geracaoUltimos13a24Meses");
        List<RetornoPadraoGraficoResponse> resultado = geracaoService.geracaoUltimos13a24Meses();
        return ResponseEntity.status(HttpStatus.OK).body(resultado);

    }

}
