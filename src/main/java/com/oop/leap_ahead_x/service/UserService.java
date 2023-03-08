package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.User;
import com.oop.leap_ahead_x.dto.UserDTO;
import com.oop.leap_ahead_x.repos.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> get(final Long uId) {
        return userRepository.findById(uId)
                .map(user -> mapToDTO(user, new UserDTO()));
    }
//
//    public Long create(final UserDTO userDTO) {
//        final User user = new User();
//        mapToEntity(userDTO, user);
//        return userRepository.save(user).getUId();
//    }
//
//    public void update(final Long uId, final UserDTO userDTO) {
//        final User user = userRepository.findById(uId)
//                .orElseThrow();
//        mapToEntity(userDTO, user);
//        userRepository.save(user);
//    }
//
//    public void delete(final Long uId) {
//        userRepository.deleteById(uId);
//    }

    // Maps the user information into the DTO format, Removing any unwanted data from getting shown
    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUId(user.getUId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

//    private User mapToEntity(final UserDTO userDTO, final User user) {
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setRole(userDTO.getRole());
//        return user;
//    }

}
