package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

@Data
@Entity
public class SeatCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_category_id")
    private Long seatCategoryId;

    @Column(name = "seat_category_name")
    private String seatCategoryName;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Column(name = "is_deleted")
    private int isDeleted;


}