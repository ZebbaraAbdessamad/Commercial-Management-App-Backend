package com.ensab.mediatech.services;

import com.ensab.mediatech.dto.ClientDto;
import com.ensab.mediatech.exceptions.EntityNotFoundException;

import java.util.Collection;


public interface ClientService {
    ClientDto save(ClientDto clientDto);

    Collection<ClientDto> list(int limit);

    ClientDto update(ClientDto clientDto) throws EntityNotFoundException;

    Boolean delete(Long id);

    ClientDto findById(Long id) throws EntityNotFoundException;

    ClientDto findByNom(String nom) throws EntityNotFoundException;
}
