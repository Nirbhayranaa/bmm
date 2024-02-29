package com.nirbhay.bmm.model.bs;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieShowSeatLockId implements Serializable {

    private Long movieShowId;
    private Long seatId;


}
