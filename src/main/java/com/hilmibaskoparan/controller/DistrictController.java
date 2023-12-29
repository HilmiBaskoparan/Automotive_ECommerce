package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.business.abstracts.DistrictService;
import com.hilmibaskoparan.business.responses.addressResponses.FindByCityIdDistrictResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts/findByCityId")
    public List<FindByCityIdDistrictResponse> findByCityId(@RequestParam int cityId){
        return districtService.findByCityId(cityId);
    }

}
