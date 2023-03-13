package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.ApplicationDTO;
import com.oop.leap_ahead_x.service.ApplicationService;
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
@RequestMapping(value = "/api/applications", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(final ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{applicationUuid}")
    public ResponseEntity<ApplicationDTO> getApplication(
            @PathVariable(name = "applicationUuid") final UUID applicationUuid) {
        return ResponseEntity.ok(applicationService.get(applicationUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createApplication(
            @RequestBody @Valid final ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(applicationService.create(applicationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{applicationUuid}")
    public ResponseEntity<Void> updateApplication(
            @PathVariable(name = "applicationUuid") final UUID applicationUuid,
            @RequestBody @Valid final ApplicationDTO applicationDTO) {
        applicationService.update(applicationUuid, applicationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{applicationUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable(name = "applicationUuid") final UUID applicationUuid) {
        applicationService.delete(applicationUuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationDTO>>  getApplicationByStatus(
            @PathVariable(name = "status") final String status) {
        return ResponseEntity.ok(applicationService.getByStatus(status));
    }




}
