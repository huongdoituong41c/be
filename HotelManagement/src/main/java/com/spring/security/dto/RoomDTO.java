package com.spring.security.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Integer id;
    private int hotelId;
    private Integer[] bookingId;
    private boolean availability;
    private String description;
}
