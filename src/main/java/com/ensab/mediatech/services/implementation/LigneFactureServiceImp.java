package com.ensab.mediatech.services.implementation;

import com.ensab.mediatech.dto.LigneFactureDto;
import com.ensab.mediatech.entities.Facture;
import com.ensab.mediatech.entities.LigneFacture;
import com.ensab.mediatech.entities.Produit;
import com.ensab.mediatech.repositories.FactureRepository;
import com.ensab.mediatech.repositories.LigneFactureRepository;
import com.ensab.mediatech.repositories.ProduitRepository;
import com.ensab.mediatech.services.LigneFactureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ensab.mediatech.exceptions.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LigneFactureServiceImp  implements LigneFactureService {
    private final ModelMapper modelMapper;
    private  final LigneFactureRepository ligneFactureRepo;
    private final FactureRepository factureRepo;
    private  final  ProduitRepository produitRepo;



    @Override
    public LigneFactureDto save(LigneFactureDto ligneFactureDto) {
        LigneFacture ligneFacture = modelMapper.map(ligneFactureDto ,LigneFacture.class);
        Facture facture = ligneFacture.getFacture();
        Produit produit = ligneFacture.getProduit();

        if (facture.getLigneFacture() == null ) {
            facture.setLigneFacture(new ArrayList<>());
        }
        if(produit.getLigneFacture() == null ) {
            produit.setLigneFacture(new ArrayList<>());
        }
        facture.getLigneFacture().add(ligneFacture);
        produit.getLigneFacture().add(ligneFacture);

        LigneFacture SavedligneFacture =ligneFactureRepo.save(ligneFacture);
        return  modelMapper.map(SavedligneFacture , LigneFactureDto.class);

    }

    @Override
    public Collection<LigneFactureDto> saveMultipleProducts(Collection<LigneFactureDto> ligneFactureDtos) throws EntityNotFoundException {
           Collection<LigneFacture> ligneFactures = new ArrayList<>();

           for (LigneFactureDto ligneFactureDto : ligneFactureDtos) {
                Facture facture = factureRepo.findById(ligneFactureDto.getFactureId()).orElseThrow(() -> new EntityNotFoundException("Facture not found"));
                Produit produit = produitRepo.findById(ligneFactureDto.getProduitId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

                LigneFacture ligneFacture = new LigneFacture();
                ligneFacture.setFacture(facture);
                ligneFacture.setProduit(produit);

                ligneFacture.setQuantite(ligneFactureDto.getQuantite());

                ligneFactures.add(ligneFacture);
            }

           Collection<LigneFacture> SavedligneFactures= ligneFactureRepo.saveAll(ligneFactures);
           return SavedligneFactures.stream().map(el->modelMapper.map(el, LigneFactureDto.class))
                   .collect(Collectors.toList());
    }

    @Override
    public Collection<LigneFactureDto> list(int limit) {
        return ligneFactureRepo.findAll(PageRequest.of(0,limit))
                .stream().map(el->modelMapper.map(el, LigneFactureDto.class))
                .collect(Collectors.toList());
    }
}
