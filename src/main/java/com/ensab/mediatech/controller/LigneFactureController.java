package com.ensab.mediatech.controller;


import com.ensab.mediatech.dto.LigneFactureDto;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.services.LigneFactureService;
import com.ensab.mediatech.shared.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/LigneFacture")
@RequiredArgsConstructor
public class LigneFactureController {

    private final LigneFactureService ligneFactureServ;


    @GetMapping("/list")
    public ResponseEntity<Response> getligneFacture()  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("ligneFacture",ligneFactureServ.list(10)))
                        .message("ligneFacture retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save-multiple-products")
    public ResponseEntity<Response> saveMultipleProducts(@RequestBody @Valid Collection<LigneFactureDto> ligneFactureDtos) throws EntityNotFoundException {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("saved",ligneFactureServ.saveMultipleProducts(ligneFactureDtos)))
                        .message("LigneFacture saved successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveLigneFacture(@RequestBody @Valid LigneFactureDto ligneFactureDto) {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("saved",ligneFactureServ.save(ligneFactureDto)))
                        .message("LigneFacture saved successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }



}
