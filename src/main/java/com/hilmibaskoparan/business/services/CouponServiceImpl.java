package com.hilmibaskoparan.business.services;

import com.hilmibaskoparan.business.abstracts.CouponService;
import com.hilmibaskoparan.business.requests.couponRequest.CreateCouponRequest;
import com.hilmibaskoparan.business.requests.couponRequest.UpdateCouponRequest;
import com.hilmibaskoparan.business.responses.PaginatedGenericResponse;
import com.hilmibaskoparan.business.responses.couponResponses.CreateCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.GetAllCouponResponse;
import com.hilmibaskoparan.business.responses.couponResponses.UpdateCouponResponse;
import com.hilmibaskoparan.core.mappers.ModelMapperService;
import com.hilmibaskoparan.model.entity.Coupon;
import com.hilmibaskoparan.repository.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final ModelMapperService modelMapperService;

    public CouponServiceImpl(CouponRepository couponRepository, ModelMapperService modelMapperService) {
        this.couponRepository = couponRepository;
        this.modelMapperService = modelMapperService;
    }

    @Transactional
    @Override
    public CreateCouponResponse addCoupon(CreateCouponRequest createCouponRequest) {

        Coupon coupon =  modelMapperService.forRequest().map(createCouponRequest, Coupon.class);;
        coupon.setCode(UUID.randomUUID().toString());
        this.couponRepository.save(coupon);

        return modelMapperService.forResponse().map(coupon, CreateCouponResponse.class);
    }

    @Override
    public UpdateCouponResponse updateCoupon(UpdateCouponRequest updateCouponRequest) {

        Coupon coupon =  modelMapperService.forRequest().map(updateCouponRequest, Coupon.class);
        coupon.setCode(this.couponRepository.findById(updateCouponRequest.getId()).get().getCode());
        this.couponRepository.save(coupon);

        return modelMapperService.forResponse().map(coupon, UpdateCouponResponse.class);
    }

    @Override
    public PaginatedGenericResponse<GetAllCouponResponse> getAllCoupon(int size, int page) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Coupon> coupon = this.couponRepository.findAll(pageable);

        List<GetAllCouponResponse> getAllCouponResponses = coupon.getContent().stream()
                .map(couponItem -> modelMapperService.forResponse().map(couponItem, GetAllCouponResponse.class) ).collect(Collectors.toList());

        return new PaginatedGenericResponse<GetAllCouponResponse>(getAllCouponResponses, coupon.getNumber(),
                coupon.getSize(), coupon.getTotalElements(), coupon.getTotalPages());
    }

    @Override
    public Coupon findByCode(String code) {
        return this.couponRepository.findByCode(code).orElse(new Coupon());
    }
}
