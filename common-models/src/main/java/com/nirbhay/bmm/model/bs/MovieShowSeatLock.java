package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movie_show_seat_lock")
@IdClass(MovieShowSeatLockId.class)
public class MovieShowSeatLock extends BaseEntity{

    @Id
    @Column(name = "movie_show_id")
    private Long movieShowId;

    @Id
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "lock_expiration_time")
    private LocalDateTime lockExpirationTime;

    @Version
    private Long version;
}
