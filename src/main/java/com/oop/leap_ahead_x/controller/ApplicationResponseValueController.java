package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.ApplicationResponseValueDTO;
import com.oop.leap_ahead_x.service.ApplicationResponseValueService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/applicationResponseValues", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationResponseValueController {

    private final ApplicationResponseValueService applicationResponseValueService;

    public ApplicationResponseValueController(
            final ApplicationResponseValueService applicationResponseValueService) {
        this.applicationResponseValueService = applicationResponseValueService;
    }

//    @GetMapping
//    public ResponseEntity<List<ApplicationResponseValueDTO>> getAllApplicationResponseValues() {
//        return ResponseEntity.ok(applicationResponseValueService.findAll());
//    }
//
//    @GetMapping("/{responseId}")
//    public ResponseEntity<ApplicationResponseValueDTO> getApplicationResponseValue(
//            @PathVariable(name = "responseId") final Integer responseId) {
//        return ResponseEntity.ok(applicationResponseValueService.get(responseId));
//    }

    @PostMapping("/saveSpecificResponse")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createResponse(@RequestBody @Valid final List<ApplicationResponseValueDTO> responseDTO) {
        applicationResponseValueService.create(responseDTO);
        String message = "Entry created successfully!";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Integer> createApplicationResponseValue(
//            @RequestBody @Valid final ApplicationResponseValueDTO applicationResponseValueDTO) {
//        return new ResponseEntity<>(applicationResponseValueService.create(applicationResponseValueDTO), HttpStatus.CREATED);
//    }

    @PutMapping("/{responseId}")
    public ResponseEntity<Void> updateApplicationResponseValue(
            @PathVariable(name = "responseId") final Integer responseId,
            @RequestBody @Valid final ApplicationResponseValueDTO applicationResponseValueDTO) {
        applicationResponseValueService.update(responseId, applicationResponseValueDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{responseId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteApplicationResponseValue(
            @PathVariable(name = "responseId") final Integer responseId) {
        applicationResponseValueService.delete(responseId);
        return ResponseEntity.noContent().build();
    }

}
