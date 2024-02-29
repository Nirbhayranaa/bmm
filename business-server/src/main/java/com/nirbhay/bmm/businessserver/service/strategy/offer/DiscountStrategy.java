package com.nirbhay.bmm.businessserver.service.strategy.offer;

import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Offer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DiscountStrategy {
    boolean isApplicable(Offer offer);

    void applyDiscount(BookingReservation bookingReservation, Offer offer);

    default BigDecimal calculateDiscount(Offer offer, BigDecimal cost) {

        BigDecimal discountAmount = BigDecimal.ZERO;
        if (isApplicable(offer)) {
            switch (offer.getOfferType().toLowerCase()) {
                case "percentage":
                    BigDecimal discountPercentage = offer.getAmount()
                            .divide(new BigDecimal("100.0"), 2, RoundingMode.HALF_UP);
                    discountAmount = cost.multiply(discountPercentage);
                    break;
                case "flat":
                    discountAmount = offer.getAmount();
                    break;
                default:
                    break;
            }
        }
        return discountAmount;

    }

}
