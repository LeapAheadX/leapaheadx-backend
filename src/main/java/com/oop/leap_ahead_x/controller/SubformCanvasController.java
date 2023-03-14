package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.SubformCanvasDTO;
import com.oop.leap_ahead_x.service.SubformCanvasService;
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
@RequestMapping(value = "/api/subformcanvas", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubformCanvasController {

    private final SubformCanvasService subformCanvasService;

    public SubformCanvasController(final SubformCanvasService subformCanvasService) {
        this.subformCanvasService = subformCanvasService;
    }

    @GetMapping
    public ResponseEntity<List<SubformCanvasDTO>> getAllSubformCanvass() {
        return ResponseEntity.ok(subformCanvasService.findAll());
    }

    @GetMapping("/{canvasUuid}")
    public ResponseEntity<SubformCanvasDTO> getSubformCanvas(
            @PathVariable(name = "canvasUuid") final UUID canvasUuid) {
        return ResponseEntity.ok(subformCanvasService.get(canvasUuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createSubformCanvas(
            @RequestBody @Valid final SubformCanvasDTO subformCanvasDTO) {
        return new ResponseEntity<>(subformCanvasService.create(subformCanvasDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{canvasUuid}")
    public ResponseEntity<Void> updateSubformCanvas(
            @PathVariable(name = "canvasUuid") final UUID canvasUuid,
            @RequestBody @Valid final SubformCanvasDTO subformCanvasDTO) {
        subformCanvasService.update(canvasUuid, subformCanvasDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{canvasUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSubformCanvas(
            @PathVariable(name = "canvasUuid") final UUID canvasUuid) {
        subformCanvasService.delete(canvasUuid);
        return ResponseEntity.noContent().build();
    }

}
