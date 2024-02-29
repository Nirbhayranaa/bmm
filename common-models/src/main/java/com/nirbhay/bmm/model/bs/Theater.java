package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;


@Data
@Entity
public class Theater extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long theaterId;

    @Column(name = "theater_name")
    private String theaterName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "is_deleted")
    private int isDeleted;

}