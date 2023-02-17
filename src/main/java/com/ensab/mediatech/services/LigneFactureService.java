package com.ensab.mediatech.services;


import com.ensab.mediatech.dto.LigneFactureDto;
import com.ensab.mediatech.exceptions.EntityNotFoundException;

import java.util.Collection;

public interface LigneFactureService {

    LigneFactureDto save(LigneFactureDto ligneFactureDto);

    Collection<LigneFactureDto> list(int limit);

    Collection<LigneFactureDto> saveMultipleProducts(Collection<LigneFactureDto> ligneFactureDtos) throws EntityNotFoundException;
}
