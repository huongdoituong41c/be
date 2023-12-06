package com.spring.security.controller;


import com.spring.security.dto.RoomDTO;
import com.spring.security.exception.BadRequestException;
import com.spring.security.exception.NotFoundException;
import com.spring.security.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public RoomDTO findById(@PathVariable("id") int id) throws BadRequestException, NotFoundException {
        return this.roomService.findRoomById(id);
    }

}
