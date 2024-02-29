package com.nirbhay.bmm.model.bs;


import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

@Data
@Entity
public class Seat extends BaseEntity {
    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "seat_id", nullable = false)
    private Long id;

    private String seatName;

    @ManyToOne
    @JoinColumn(name = "seat_category_id")
    private SeatCategory seatCategory;

}