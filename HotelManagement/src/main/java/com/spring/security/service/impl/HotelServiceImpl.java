package com.spring.security.service.impl;

import com.spring.security.dto.BookingDTO;
import com.spring.security.dto.HotelDTO;
import com.spring.security.dto.RoomTypeDTO;
import com.spring.security.dto.request.HotelRequestDTO;
import com.spring.security.dto.response.HotelResponseDTO;
import com.spring.security.entity.Hotel;
import com.spring.security.enumeration.EApplicationError;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import com.spring.security.repository.HotelRepository;
import com.spring.security.service.HotelService;
import com.spring.security.validation.HotelValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HotelValidation hotelValidation;



    @Override
    public HotelRequestDTO saveHotel(HotelRequestDTO requestDTO) throws BadRequestException, IOException, NotFoundException {
        hotelValidation.validateRequestHotel(requestDTO);
        Hotel hotel = this.modelMapper.map(requestDTO, Hotel.class);
        hotel.setImg(requestDTO.getImg().getBytes());
        return this.modelMapper.map(this.hotelRepository.save(hotel),HotelRequestDTO.class);
    }

    @Override
    public HotelResponseDTO getHotelById(Integer id) throws NotFoundException {
        Optional<Hotel> hotel = hotelRepository.findById(Long.valueOf(id));
        if (!hotel.isPresent()) {
            throw new NotFoundException(EApplicationError.HOTEL_NOT_FOUND.getErrorObject());
        }
        return this.modelMapper.map(hotel, HotelResponseDTO.class);
    }

    public List<Hotel> getHotel(HotelDTO hotelDTO, BookingDTO bookingDTO, RoomTypeDTO roomTypeDTO) {
        return this.hotelRepository.getHotel(hotelDTO.getAddress(),bookingDTO.getCheckInDate(),bookingDTO.getCheckOutDate(), roomTypeDTO.getId());
    }

}
