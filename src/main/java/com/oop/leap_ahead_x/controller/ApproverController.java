package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.ApproverDTO;
import com.oop.leap_ahead_x.service.ApproverService;
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
@RequestMapping(value = "/api/approvers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApproverController {

    private final ApproverService approverService;

    public ApproverController(final ApproverService approverService) {
        this.approverService = approverService;
    }

    @GetMapping
    public ResponseEntity<List<ApproverDTO>> getAllApprovers() {
        return ResponseEntity.ok(approverService.findAll());
    }

    @GetMapping("/{approverUuid}")
    public ResponseEntity<ApproverDTO> getApprover(
            @PathVariable(name = "approverUuid") final UUID approverUuid) {
        return ResponseEntity.ok(approverService.get(approverUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createApprover(@RequestBody @Valid final ApproverDTO approverDTO) {
        return new ResponseEntity<>(approverService.create(approverDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{approverUuid}")
    public ResponseEntity<Void> updateApprover(
            @PathVariable(name = "approverUuid") final UUID approverUuid,
            @RequestBody @Valid final ApproverDTO approverDTO) {
        approverService.update(approverUuid, approverDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{approverUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteApprover(
            @PathVariable(name = "approverUuid") final UUID approverUuid) {
        approverService.delete(approverUuid);
        return ResponseEntity.noContent().build();
    }

}
