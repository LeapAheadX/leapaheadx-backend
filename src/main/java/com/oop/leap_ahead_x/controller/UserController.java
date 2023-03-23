package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.UserDTO;
import com.oop.leap_ahead_x.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{uId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "uId") final UUID uId) {
        return ResponseEntity.ok(userService.get(uId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{uId}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "uId") final UUID uId,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(uId, userDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unlock/{uId}")
    public ResponseEntity<Void> unlock(
            @PathVariable(name = "uId") final UUID uId) {
        userService.unlock(uId);
        return ResponseEntity.ok().build();
    }

    //Vendor save application
    @PutMapping("/lock/{uId}")
    public ResponseEntity<Void> lock(
            @PathVariable(name = "uId") final UUID uId) {
        userService.lock(uId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{uId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "uId") final UUID uId) {
        userService.delete(uId);
        return ResponseEntity.noContent().build();
    }

}
