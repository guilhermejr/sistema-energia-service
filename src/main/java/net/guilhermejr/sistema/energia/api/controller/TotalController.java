package net.guilhermejr.sistema.energia.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.response.TotalResponse;
import net.guilhermejr.sistema.energia.exception.dto.ErrorDefaultDTO;
import net.guilhermejr.sistema.energia.service.TotalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/total")
public class TotalController {

    private final TotalService totalService;

    // --- Retornar -----------------------------------------------------------
    @GetMapping
    public ResponseEntity<TotalResponse> retornar() {

        log.info("Retornando total");
        TotalResponse totalResponse = totalService.retornar();
        return ResponseEntity.status(HttpStatus.OK).body(totalResponse);

    }

}
