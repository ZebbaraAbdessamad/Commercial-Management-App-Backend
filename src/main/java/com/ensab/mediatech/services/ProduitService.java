package com.ensab.mediatech.services;
import com.ensab.mediatech.dto.ProduitDto;
import com.ensab.mediatech.exceptions.EntityAlreadyExistException;
import com.ensab.mediatech.exceptions.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProduitService {
    ProduitDto save(ProduitDto produitDto) throws EntityAlreadyExistException;
    Boolean delete(Long id);
    ProduitDto update(ProduitDto produitDto) throws EntityNotFoundException;
    Collection<ProduitDto> list(int limit);
    ProduitDto findByReference(String reference) throws EntityNotFoundException;
    ProduitDto findByLibelle(String libelle) throws EntityNotFoundException;
    List<ProduitDto> findByPrixGreaterThan(BigDecimal value);
}
