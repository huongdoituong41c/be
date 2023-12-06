package com.spring.security.service;

import com.spring.security.dto.BookingDTO;
import com.spring.security.dto.HotelDTO;
import com.spring.security.dto.request.HotelRequestDTO;
import com.spring.security.dto.response.HotelResponseDTO;
import com.spring.security.dto.RoomTypeDTO;
import com.spring.security.entity.Hotel;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface HotelService {

     HotelRequestDTO saveHotel(HotelRequestDTO hotelDTO) throws BadRequestException, IOException, NotFoundException;
     HotelResponseDTO getHotelById(Integer id) throws NotFoundException;

     List<Hotel> getHotel(HotelDTO hotelDTO, BookingDTO bookingDTO, RoomTypeDTO roomTypeDTO);
}
