package com.ensab.mediatech.controller;

import com.ensab.mediatech.dto.ClientDto;
import com.ensab.mediatech.shared.Response;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {
    private final ModelMapper modelMapper;
    private final ClientService clientServ;
    private final String imageDirectory = "C:/images/";


    @GetMapping("/list")
    public ResponseEntity<Response> getClients() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
     return ResponseEntity.ok(
             Response.builder()
                     .timeStamp(LocalDateTime.now())
                     .data(Map.of("clients",clientServ.list(10)))
                     .message("clients retrieved")
                     .status(HttpStatus.OK)
                     .statusCode(HttpStatus.OK.value())
                     .build()
     );
    }


    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveClient(@RequestPart("image") MultipartFile image, @RequestPart("clientDto")  ClientDto clientDto) {
       //     System.out.println(clientDto);
        try {
            // Save the image to a folder on the server
            String fileName = image.getOriginalFilename();
            byte[] bytes = image.getBytes();
            Path path = Paths.get(imageDirectory + fileName);
            Files.write(path, bytes);
            clientDto.setImage(setServerImageUrl(fileName));
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("client",clientServ.save(clientDto)))
                            .message("client created !")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );

        } catch (IOException e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }

    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Response> getClientByNom( @PathVariable String nom) throws EntityNotFoundException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("client",clientServ.findByNom(nom)))
                        .message("client is found")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value())
                        .build()
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> getClientById( @PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("client",clientServ.findById(id)))
                        .message("client is found by id")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value())
                        .build()
        );
    }





    @PutMapping(value = "/update" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateClient(@RequestPart("image") MultipartFile image, @RequestPart("clientDto")  @Valid ClientDto clientDto) throws EntityNotFoundException {
        try {
            // Save the image to a folder on the server
            String fileName = image.getOriginalFilename();
            byte[] bytes = image.getBytes();
            Path path = Paths.get(imageDirectory + fileName);
            Files.write(path, bytes);
            clientDto.setImage(setServerImageUrl(fileName));
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("updated",clientServ.update(clientDto)))
                        .message("client updated successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

        } catch (IOException e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
    }
/*
    @PutMapping("/update")
    public ResponseEntity<Response> updateClient( @RequestBody @Valid ClientDto clientDto) throws EntityNotFoundException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("updated",clientServ.update(clientDto)))
                        .message("client updated successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteClient( @PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("deleted",clientServ.delete(id)))
                        .message("client deleted successfuly")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}",produces = IMAGE_PNG_VALUE)
    public byte[] getServer(@PathVariable("fileName") String fileName ) throws IOException {
        return Files.readAllBytes(Paths.get(imageDirectory + fileName));
    }

    private String setServerImageUrl( String ImageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/client/image/"+ImageName).toUriString();
    }


}
