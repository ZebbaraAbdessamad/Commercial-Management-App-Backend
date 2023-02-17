package com.ensab.mediatech.services.implementation;

import com.ensab.mediatech.dto.ProduitDto;
import com.ensab.mediatech.entities.Produit;
import com.ensab.mediatech.exceptions.EntityAlreadyExistException;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.repositories.ProduitRepository;
import com.ensab.mediatech.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitServiceImp implements ProduitService {

    private final ProduitRepository produitRepo;
    private final ModelMapper modelMapper;

    @Override
    public ProduitDto save(ProduitDto produitDto) throws EntityAlreadyExistException {
        Optional<Produit> produit = produitRepo.findByReference(produitDto.getReference());
        if (produit != null){
            Produit produit1  = modelMapper.map(produitDto ,Produit.class);
            Produit prdSaved = produitRepo.save(produit1);
            return modelMapper.map(prdSaved ,ProduitDto.class);
        }else{
            throw  new EntityAlreadyExistException("this product is aleardy exist!!");
        }

    }

    @Override
    public Boolean delete(Long id) {
        produitRepo.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ProduitDto update(ProduitDto produitDto) throws EntityNotFoundException {
        Optional<Produit>  produit = produitRepo.findById(produitDto.getId());
        if (produit.isPresent()){
            Produit produit1 = modelMapper.map(produitDto ,Produit.class);
            Produit prdUpdate = produitRepo.save(produit1);
            return modelMapper.map(prdUpdate , ProduitDto.class);
        }else {
            throw  new EntityNotFoundException("produit Not Found !!");
        }

    }

    @Override
    public ProduitDto findByReference(String reference) throws EntityNotFoundException {
      Produit produit = produitRepo.findByReference(reference).orElseThrow(
              ()->new EntityNotFoundException("produit with reference "+"'"+reference+"'"+" not found")
      );
        return  modelMapper.map(produit , ProduitDto.class);
    }

    @Override
    public ProduitDto findByLibelle(String libelle) throws EntityNotFoundException {
        Produit produit = produitRepo.findByLibelle(libelle).orElseThrow(
                ()->new EntityNotFoundException("produit with libelle "+"'"+libelle+"'"+" not found")
        );
        return modelMapper.map(produit ,ProduitDto.class);
    }

    @Override
    public Collection<ProduitDto> list(int limit) {
        return produitRepo.findAll(PageRequest.of(0,limit))
                .stream().map(el->modelMapper.map(el, ProduitDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProduitDto> findByPrixGreaterThan(BigDecimal value) {
        return null;
    }
}
