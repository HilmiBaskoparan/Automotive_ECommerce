package com.hilmibaskoparan.business.rules;

import com.hilmibaskoparan.exception.BusinessExcepiton;
import com.hilmibaskoparan.model.entity.Coupon;
import com.hilmibaskoparan.repository.CouponRepository;
import com.hilmibaskoparan.util.MessageProvider;
import com.hilmibaskoparan.util.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CouponRules {

    @Autowired
    private MessageProvider messageProvider;
    private final CouponRepository couponRepository;

    public CouponRules(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void checkCouponDateExpiry(Coupon coupon) {
        if (coupon.getExpiresAt() != null) {
            if (coupon.getExpiresAt().isBefore(LocalDate.now())) {
                throw new BusinessExcepiton("0001", messageProvider.getMessage("0001", MessageStatus.ERRORS),
                        HttpStatus.BAD_REQUEST);
            }
        }
    }

    public void checkCouponValidity(String couponCode) {

        if(	this.couponRepository.findByCode(couponCode).isEmpty()) {
            throw new BusinessExcepiton("0002",messageProvider.getMessage("0002", MessageStatus.ERRORS),HttpStatus.BAD_REQUEST );
        }

    }
}
