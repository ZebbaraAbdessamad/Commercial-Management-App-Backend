package com.ensab.mediatech.services;


import com.ensab.mediatech.dto.FactureDto;

import java.util.Collection;


public interface FactureService {

    FactureDto save(FactureDto factureDto);

    Collection<FactureDto> list(int limit);
}
