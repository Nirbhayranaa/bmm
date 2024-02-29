package com.nirbhay.bmm.model.bs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nirbhay.bmm.model.BaseEntity;
import com.nirbhay.bmm.model.usermanagement.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_reservation")
public class BookingReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShow movieShow;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @Version
    private int version;

    private BigDecimal cost;
    private BigDecimal discountAmount = BigDecimal.ZERO;
    private int ticketIndex;

    @Column(name = "is_deleted")
    private int isDeleted;
}
