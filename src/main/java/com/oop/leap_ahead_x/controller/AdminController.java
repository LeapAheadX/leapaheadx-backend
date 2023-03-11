package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.AdminDTO;
import com.oop.leap_ahead_x.service.AdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final AdminService adminService;

    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/{adminUuid}")
    public ResponseEntity<AdminDTO> getAdmin(
            @PathVariable(name = "adminUuid") final UUID adminUuid) {
        return ResponseEntity.ok(adminService.get(adminUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createAdmin(@RequestBody @Valid final AdminDTO adminDTO) {
        return new ResponseEntity<>(adminService.create(adminDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{adminUuid}")
    public ResponseEntity<Void> updateAdmin(@PathVariable(name = "adminUuid") final UUID adminUuid,
            @RequestBody @Valid final AdminDTO adminDTO) {
        adminService.update(adminUuid, adminDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{adminUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAdmin(
            @PathVariable(name = "adminUuid") final UUID adminUuid) {
        adminService.delete(adminUuid);
        return ResponseEntity.noContent().build();
    }

}
