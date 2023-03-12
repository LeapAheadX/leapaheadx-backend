package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.FormWorkflowDTO;
import com.oop.leap_ahead_x.service.FormWorkflowService;
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
@RequestMapping(value = "/api/formWorkflows", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormWorkflowController {

    private final FormWorkflowService formWorkflowService;

    public FormWorkflowController(final FormWorkflowService formWorkflowService) {
        this.formWorkflowService = formWorkflowService;
    }

    @GetMapping
    public ResponseEntity<List<FormWorkflowDTO>> getAllFormWorkflows() {
        return ResponseEntity.ok(formWorkflowService.findAll());
    }

    @GetMapping("/{formUuid}")
    public ResponseEntity<FormWorkflowDTO> getFormWorkflow(
            @PathVariable(name = "formUuid") final UUID formUuid) {
        return ResponseEntity.ok(formWorkflowService.get(formUuid));
    }

    //get all forms,input componenets,options
    @GetMapping("/forms")
    public ResponseEntity<String> getEmptyForms() {
        return formWorkflowService.getEmptyForms();
    }

    //get form by form id
    @GetMapping("/forms/{fId}")
    public ResponseEntity<String> getFormById(@PathVariable(name = "fId")final UUID fId) {
        return formWorkflowService.getFormById(fId);
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createFormWorkflow(
            @RequestBody @Valid final FormWorkflowDTO formWorkflowDTO) {
        return new ResponseEntity<>(formWorkflowService.create(formWorkflowDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{formUuid}")
    public ResponseEntity<Void> updateFormWorkflow(
            @PathVariable(name = "formUuid") final UUID formUuid,
            @RequestBody @Valid final FormWorkflowDTO formWorkflowDTO) {
        formWorkflowService.update(formUuid, formWorkflowDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{formUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFormWorkflow(
            @PathVariable(name = "formUuid") final UUID formUuid) {
        formWorkflowService.delete(formUuid);
        return ResponseEntity.noContent().build();
    }

}
