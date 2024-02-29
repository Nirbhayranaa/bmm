package com.nirbhay.bmm.model.bs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "seat_price")
public class SeatPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_price_id")
    private Long seatPriceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_show_id", nullable = false)
    private MovieShow movieShow;

    @ManyToOne
    @JoinColumn(name = "seat_category_id", nullable = false)
    @JsonIgnoreProperties("screen")
    private SeatCategory seatCategory;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
