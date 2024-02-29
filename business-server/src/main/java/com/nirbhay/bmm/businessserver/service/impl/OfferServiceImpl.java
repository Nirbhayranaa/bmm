package com.nirbhay.bmm.businessserver.service.impl;

import com.nirbhay.bmm.businessserver.dao.OfferDao;
import com.nirbhay.bmm.businessserver.service.OfferService;
import com.nirbhay.bmm.businessserver.service.strategy.offer.DiscountStrategy;
import com.nirbhay.bmm.model.bs.BookingReservation;
import com.nirbhay.bmm.model.bs.Offer;
import com.nirbhay.bmm.model.bs.Payment;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nirbhay Rana
 */
@Service
public class OfferServiceImpl implements OfferService {


    private final List<DiscountStrategy> discountStrategies;
    private final OfferDao offerDao;

    public OfferServiceImpl(List<DiscountStrategy> discountStrategies, OfferDao offerDao) {
        this.discountStrategies = discountStrategies;
        this.offerDao = offerDao;
    }


    @Override
    public Payment applyOffer(List<BookingReservation> bookingReservations) {
        List<Offer> offers = offerDao.findAll();

        for (BookingReservation bookingReservation : bookingReservations) {
            for (Offer offer : offers) {
                for (DiscountStrategy discountStrategy : discountStrategies) {
                    discountStrategy.applyDiscount(bookingReservation, offer);
                }
            }
        }

        BigDecimal totalDiscountedAmount = bookingReservations.stream()
                .map(BookingReservation::getDiscountAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Payment payment = new Payment();
        payment.setActualAmount(calculateTotalAmount(bookingReservations));
        payment.setOfferAmount(totalDiscountedAmount);
        payment.setFinalAmount(payment.getActualAmount().subtract(totalDiscountedAmount));
        return payment;
    }

    private BigDecimal calculateTotalAmount(List<BookingReservation> bookingReservations) {
        return bookingReservations.stream()
                .map(BookingReservation::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
