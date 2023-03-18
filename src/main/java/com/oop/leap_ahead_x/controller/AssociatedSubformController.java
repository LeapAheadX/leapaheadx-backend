package com.oop.leap_ahead_x.controller;
import com.oop.leap_ahead_x.dto.AssociatedSubformDTO;
import com.oop.leap_ahead_x.service.AssociatedSubformService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/associatedSubform", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssociatedSubformController {


    private final AssociatedSubformService associatedSubformService;
    public AssociatedSubformController(final AssociatedSubformService associatedSubformService) {
        this.associatedSubformService = associatedSubformService;
    }

    @GetMapping()
    public ResponseEntity<List<AssociatedSubformDTO>> getAllAssociatedSubform() {
        return ResponseEntity.ok(associatedSubformService.findAll());
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createFormStep(@RequestBody @Valid final AssociatedSubformDTO associatedSubformDTO) {
        return new ResponseEntity<>(associatedSubformService.create(associatedSubformDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{associatedSubformsId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFormWorkflow(
            @PathVariable(name = "associatedSubformsId") final Integer associatedId) {
        associatedSubformService.delete(associatedId);
        return ResponseEntity.noContent().build();
    }

}
