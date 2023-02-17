package com.ensab.mediatech.controller;

import com.ensab.mediatech.dto.FactureDto;
import com.ensab.mediatech.services.FactureService;
import com.ensab.mediatech.shared.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/facture")
@RequiredArgsConstructor
public class FactureController {
    private final FactureService factureServ;


    @GetMapping("/list")
    public ResponseEntity<Response> getFactures()  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("factures",factureServ.list(10)))
                        .message("factures retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveClient(@RequestBody @Valid FactureDto factureDto)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("facture",factureServ.save(factureDto)))
                        .message("Facture created !")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

}
