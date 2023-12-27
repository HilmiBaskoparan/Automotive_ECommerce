package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.DistrictService;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCityIdDistrictResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.District;
import com.hilmibaskoparan.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelMapperService modelMapperService;

    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapperService modelMapperService) {
        this.districtRepository = districtRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<FindByCityIdDistrictResponse> findByCityId(int cityId) {

        List<District> districts = districtRepository.findAll();

        List<FindByCityIdDistrictResponse> response = districts.stream()
                .map(district -> modelMapperService.forResponse().map(district, FindByCityIdDistrictResponse.class))
                .collect(Collectors.toList());

        return response;
    }
}
