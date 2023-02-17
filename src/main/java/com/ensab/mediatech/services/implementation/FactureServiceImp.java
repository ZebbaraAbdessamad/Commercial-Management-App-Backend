package com.ensab.mediatech.services.implementation;


import com.ensab.mediatech.dto.FactureDto;
import com.ensab.mediatech.dto.LigneFactureDto;
import com.ensab.mediatech.entities.Client;
import com.ensab.mediatech.entities.Facture;
import com.ensab.mediatech.repositories.FactureRepository;
import com.ensab.mediatech.services.FactureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FactureServiceImp implements FactureService {

    private final ModelMapper modelMapper;
    private  final FactureRepository factureRepo;


    @Override
    public FactureDto save(FactureDto factureDto) {
        Facture facture = modelMapper.map(factureDto, Facture.class);
        Client client = facture.getClient();
        if (client.getFactures() == null) {
            client.setFactures(new ArrayList<>());
        }
        client.getFactures().add(facture);
        Facture savedFacture = factureRepo.save(facture);
        return modelMapper.map(savedFacture, FactureDto.class);
    }

    @Override
    public Collection<FactureDto> list(int limit) {
        return factureRepo.findAll(PageRequest.of(0,limit))
                .stream().map(el->modelMapper.map(el, FactureDto.class))
                .collect(Collectors.toList());
    }

}
