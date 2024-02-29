package com.nirbhay.bmm.model.bs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nirbhay.bmm.model.BaseEntity;
import com.nirbhay.bmm.model.usermanagement.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


/**
 * @author Nirbhay Rana
 */
@Data
@Entity
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShow movieShow;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private LocalDate bookingDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "booking")
    private List<BookingReservation> bookingReservations;

    @Column(name = "is_deleted")
    private int isDeleted;
}
