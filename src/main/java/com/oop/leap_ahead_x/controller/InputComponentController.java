package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.InputComponentDTO;
import com.oop.leap_ahead_x.service.InputComponentService;
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
@RequestMapping(value = "/api/inputcomponents", produces = MediaType.APPLICATION_JSON_VALUE)
public class InputComponentController {

    private final InputComponentService inputComponentService;

    public InputComponentController(final InputComponentService inputComponentService) {
        this.inputComponentService = inputComponentService;
    }

    @GetMapping
    public ResponseEntity<List<InputComponentDTO>> getAllInputComponents() {
        return ResponseEntity.ok(inputComponentService.findAll());
    }

    @GetMapping("/{componentUuid}")
    public ResponseEntity<InputComponentDTO> getInputComponent(
            @PathVariable(name = "componentUuid") final UUID componentUuid) {
        return ResponseEntity.ok(inputComponentService.get(componentUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createInputComponent(
            @RequestBody @Valid final InputComponentDTO inputComponentDTO) {
        return new ResponseEntity<>(inputComponentService.create(inputComponentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{componentUuid}")
    public ResponseEntity<Void> updateInputComponent(
            @PathVariable(name = "componentUuid") final UUID componentUuid,
            @RequestBody @Valid final InputComponentDTO inputComponentDTO) {
        inputComponentService.update(componentUuid, inputComponentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{componentUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInputComponent(
            @PathVariable(name = "componentUuid") final UUID componentUuid) {
        inputComponentService.delete(componentUuid);
        return ResponseEntity.noContent().build();
    }

}
