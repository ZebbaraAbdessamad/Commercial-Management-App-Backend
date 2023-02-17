package com.ensab.mediatech.controller;

import com.ensab.mediatech.dto.ProduitDto;
import com.ensab.mediatech.exceptions.EntityAlreadyExistException;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.services.ProduitService;
import com.ensab.mediatech.shared.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produit")
public class ProduitController {
    private final ProduitService produitServ;


    @PostMapping("/save")
    public ResponseEntity<Response> saveProduit(@RequestBody @Valid ProduitDto produitDto) throws EntityAlreadyExistException {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("saved",produitServ.save(produitDto)))
                        .message("produit saved successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllProduit(){
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("products",produitServ.list(10)))
                        .message("products retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/reference/{reference}")
    public ResponseEntity<Response> getProduitByReference(@PathVariable("reference") String Ref) throws EntityNotFoundException {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("produit",produitServ.findByReference(Ref)))
                        .message("produit is found by reference successfuly")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value())
                        .build()
        );
    }

    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<Response> getProduitByLibelle(@PathVariable("libelle") String libelle) throws EntityNotFoundException {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("produit",produitServ.findByLibelle(libelle)))
                        .message("produit is found by libelle successfuly")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value())
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateProduit(@RequestBody @Valid ProduitDto produitDto) throws EntityNotFoundException {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("updated",produitServ.update(produitDto)))
                        .message("produit updated successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduitById(@PathVariable Long id) {
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("produit",produitServ.delete(id)))
                        .message("produit is deleted successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }







}
