package com.spring.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HotelRequestDTO {
    private Integer id;
    @NotBlank(message = "Name is not empty")
    private String name;
    @NotBlank(message = "Address is not empty")
    private String address;
    private String description;
    private MultipartFile img ;
}
