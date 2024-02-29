package com.nirbhay.bmm.model.bs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Nirbhay Rana
 */
@Data
@Entity
public class Payment  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference
    private Booking booking;

    @Column(name = "actual_amount", nullable = false)
    private BigDecimal actualAmount;

    @Column(name = "offer_amount")
    private BigDecimal offerAmount;

    @Column(name = "final_amount", nullable = false)
    private BigDecimal finalAmount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

}
