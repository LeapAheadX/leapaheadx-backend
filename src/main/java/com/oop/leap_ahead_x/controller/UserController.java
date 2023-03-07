package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.UserDTO;
import com.oop.leap_ahead_x.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

//    @GetMapping("/{uId}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "uId") final Long uId) {
//        return ResponseEntity.ok(userService.get(uId));
//    }
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
//        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{uId}")
//    public ResponseEntity<Void> updateUser(@PathVariable(name = "uId") final Long uId,
//                                           @RequestBody @Valid final UserDTO userDTO) {
//        userService.update(uId, userDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{uId}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteUser(@PathVariable(name = "uId") final Long uId) {
//        userService.delete(uId);
//        return ResponseEntity.noContent().build();
//    }

}
