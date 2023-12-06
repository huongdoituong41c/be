package com.spring.security.validation;

import com.spring.security.dto.request.HotelRequestDTO;
import com.spring.security.entity.Hotel;
import com.spring.security.enumeration.EApplicationError;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import com.spring.security.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class HotelValidation {
    @Autowired
    private HotelRepository hotelRepository;

    public void validateRequestHotel(HotelRequestDTO dto) throws BadRequestException, NotFoundException {

        // validate exist hotel (update mode)
        if(Objects.nonNull(dto.getId())){
            Optional<Hotel> optionalHotel = hotelRepository.findById(Long.valueOf(dto.getId()));
            if(!optionalHotel.isPresent()){
                throw new NotFoundException(EApplicationError.HOTEL_NOT_FOUND.getErrorObject());
            }
        }

        if (dto.getImg().isEmpty()) {
            throw  new BadRequestException(EApplicationError.IMAGE_NOT_NULL.getErrorObject());
        }



    }
}
