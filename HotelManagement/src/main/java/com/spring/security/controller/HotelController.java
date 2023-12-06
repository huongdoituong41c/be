package com.spring.security.controller;

import com.spring.security.dto.BookingDTO;
import com.spring.security.dto.HotelDTO;
import com.spring.security.dto.RoomTypeDTO;
import com.spring.security.dto.request.HotelRequestDTO;
import com.spring.security.dto.response.HotelResponseDTO;
import com.spring.security.entity.Hotel;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import com.spring.security.service.HotelService;
import com.spring.security.validation.HotelValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelValidation hotelValidation;


    @PostMapping("/create")
    public HotelRequestDTO createHotel(@ModelAttribute @Valid HotelRequestDTO dto) throws BadRequestException, IOException, NotFoundException {
        return this.hotelService.saveHotel(dto);
    }

    @PutMapping("/update")
    public HotelRequestDTO updateHotel(@ModelAttribute @Valid HotelRequestDTO dto) throws BadRequestException, IOException, NotFoundException {
        return  this.hotelService.saveHotel(dto);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getHotelImage(@PathVariable Integer id) throws NotFoundException {
        HotelResponseDTO responseDTO = hotelService.getHotelById(id);

        if (responseDTO != null && responseDTO.getImg() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(responseDTO.getImg().length);
            return new ResponseEntity<>(responseDTO.getImg(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getHotel/{address}/{id}")
    public ResponseEntity<?> getHotel(@RequestBody BookingDTO bookingDTO, @PathVariable("address")String address, @PathVariable("id")Integer roomType) {
        HotelDTO hotelDTO = new HotelDTO();
        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
        try{
            hotelDTO.setAddress(address);
            roomTypeDTO.setId(roomType);
            List<Hotel> hotels = this.hotelService.getHotel(hotelDTO, bookingDTO, roomTypeDTO);
            if (hotels.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("The rooms aren't available");
            } else {
                return ResponseEntity.ok(hotels);
            }

        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.EXPECTATION_FAILED);
        }

    }
}
