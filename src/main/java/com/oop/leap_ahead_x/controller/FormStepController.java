package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.FormStepDTO;
import com.oop.leap_ahead_x.service.FormStepService;
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
@RequestMapping(value = "/api/formSteps", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormStepController {

    private final FormStepService formStepService;

    public FormStepController(final FormStepService formStepService) {
        this.formStepService = formStepService;
    }

    @GetMapping("/original")
    public ResponseEntity<List<FormStepDTO>> getAllFormSteps() {
        return ResponseEntity.ok(formStepService.findAll());
    }

//    @GetMapping("/{stepUuid}")
//    public ResponseEntity<FormStepDTO> getFormStep(
//            @PathVariable(name = "stepUuid") final UUID stepUuid) {
//        return ResponseEntity.ok(formStepService.get(stepUuid));
//    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createFormStep(@RequestBody @Valid final FormStepDTO formStepDTO) {
        return new ResponseEntity<>(formStepService.create(formStepDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{stepUuid}")
    public ResponseEntity<Void> updateFormStep(@PathVariable(name = "stepUuid") final UUID stepUuid,
            @RequestBody @Valid final FormStepDTO formStepDTO) {
        formStepService.update(stepUuid, formStepDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stepUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFormStep(
            @PathVariable(name = "stepUuid") final UUID stepUuid) {
        formStepService.delete(stepUuid);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<String> getAllSubstepDetails(){
        return formStepService.getAllSubstepDetails();
    }

    @GetMapping("/{stepUuid}")
    public ResponseEntity<String> getSubstepDetails(@PathVariable(name = "stepUuid") final UUID stepUuid){
        return formStepService.getSubstepDetails(stepUuid);
    }





}
