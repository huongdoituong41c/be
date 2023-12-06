package com.spring.security.service.impl;

import com.spring.security.config.ModelMapperConfig;
import com.spring.security.dto.UserDTO;
import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ModelMapperConfig modelMapper;


    @Override
    public boolean registerUser (UserDTO userDTO)  {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return false;
        }
        User user = modelMapper.modelMapper().map(userDTO, User.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);
        return true;
    };

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
    public boolean checkPassword(String password, String hash) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, hash);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public boolean login(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            if (checkPassword(userDTO.getPassword(),userRepository.getPassword(userDTO.getEmail()))) {
                return true;
            }
        }
        return false;
    }
}
