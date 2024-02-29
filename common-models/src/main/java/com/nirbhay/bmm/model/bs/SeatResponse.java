package com.nirbhay.bmm.model.bs;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nirbhay Rana
 */
@Data
@Builder
public class SeatResponse {
    private long seatId;
    private String seatName;
    private long seatCategoryId;
    private String seatCategoryName;
    private boolean isBooked;
}
