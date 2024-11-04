package net.guilhermejr.sistema.energia.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.response.GeracaoResponse;
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

    // --- Retornar -----------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<GeracaoResponse>> retornar() {

        log.info("Retornando geração");
        List<GeracaoResponse> geracaoResponse = geracaoService.retornar();
        return ResponseEntity.status(HttpStatus.OK).body(geracaoResponse);

    }

}
