package com.nirbhay.bmm.model.bs;

import com.nirbhay.bmm.model.usermanagement.AppUser;
import lombok.Data;

import java.util.Set;

@Data
public class BookingRequest {
    private Long movieShowId;
    private Long userId;
    private Set<Long> seatId;
}
