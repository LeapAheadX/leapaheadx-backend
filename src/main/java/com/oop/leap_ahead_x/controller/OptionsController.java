package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.OptionsDTO;
import com.oop.leap_ahead_x.service.OptionsService;
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
@RequestMapping(value = "/api/optionss", produces = MediaType.APPLICATION_JSON_VALUE)
public class OptionsController {

    private final OptionsService optionsService;

    public OptionsController(final OptionsService optionsService) {
        this.optionsService = optionsService;
    }

    @GetMapping
    public ResponseEntity<List<OptionsDTO>> getAllOptionss() {
        return ResponseEntity.ok(optionsService.findAll());
    }

    @GetMapping("/{optionUuid}")
    public ResponseEntity<OptionsDTO> getOptions(
            @PathVariable(name = "optionUuid") final UUID optionUuid) {
        return ResponseEntity.ok(optionsService.get(optionUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createOptions(@RequestBody @Valid final OptionsDTO optionsDTO) {
        return new ResponseEntity<>(optionsService.create(optionsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{optionUuid}")
    public ResponseEntity<Void> updateOptions(
            @PathVariable(name = "optionUuid") final UUID optionUuid,
            @RequestBody @Valid final OptionsDTO optionsDTO) {
        optionsService.update(optionUuid, optionsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{optionUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOptions(
            @PathVariable(name = "optionUuid") final UUID optionUuid) {
        optionsService.delete(optionUuid);
        return ResponseEntity.noContent().build();
    }

}
