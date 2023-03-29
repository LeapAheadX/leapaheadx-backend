package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.ApplicationDTO;
import com.oop.leap_ahead_x.service.ApplicationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
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

    //Get an array of subcanvas id which needs to be filled up by the assigned person at the current step. returns the form Id which is used to render the form
    //http://localhost:8080/api/applications/79ec03aa-bd58-11ed-afa1-0242ac120002/user/79ebaad6-bd58-11ed-afa1-0242ac120002
    @GetMapping("/{step}/{fId}")
    public ResponseEntity<String> getAssignedSubcanvas(
            @PathVariable(name = "step") final int step,@PathVariable(name = "fId") final UUID fId) {
        return applicationService.getAssignedSubcanvas(step,fId);
    }

    //Get an array of applicationID,formID,formName,status,currentstepNumber based on the vendorID
    //http://localhost:8080/api/applications/vendor/79ebb4e0-bd58-11ed-afa1-0242ac120002
    @GetMapping("/vendor/{vId}")
    public ResponseEntity<String> getApplicationByVendor(
            @PathVariable(name = "vId") final UUID vId) {
        return applicationService.getApplicationByVendor(vId);
    }

    @GetMapping("/vendor/{vId}/{status}")
    public ResponseEntity<String> getApplicationByVendorAndStatus(
            @PathVariable(name = "vId") final UUID vId, @PathVariable(name = "status") final String status) {
        return applicationService.getApplicationByVendorAndStatus(vId, status);
    }

    //Get an array of applicationID,formID,formName,status,currentstepNumber based on the vendorID
    //http://localhost:8080/api/applications/getFullForm/79ec053a-bd58-11ed-afa1-0242ac120002
    @GetMapping("/getFullForm/{applicationUuid}")
    public ResponseEntity<String> getApplicationWithAllDetails(
            @PathVariable(name = "applicationUuid") final UUID aId) {
        return applicationService.getApplicationWithAllDetails(aId);
    }

    //get current assignee type of application using application UUID
    @GetMapping("/assignee/{applicationUuid}")
    public ResponseEntity<String> getApplicationCurrentAssigneeType(
            @PathVariable(name = "applicationUuid") final UUID aId) {
        return applicationService.getApplicationCurrentAssigneeType(aId);
    }


    //save application
    @PutMapping("/Save/{aId}")
    public ResponseEntity<Void> saveVendor(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.Save(aId);
        return ResponseEntity.ok().build();
    }

    //submit application
    @PutMapping("/Submit/{aId}")
    public ResponseEntity<Void> Submit(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.Submit(aId);
        return ResponseEntity.ok().build();
    }

    //reject application
    @PutMapping("/reject/{aId}")
    public ResponseEntity<String> Reject(@PathVariable("aId") UUID aId, @RequestBody Map<String, String> requestBody) {
        applicationService.Reject(aId, requestBody.get("comments"));
        return ResponseEntity.ok("Comments updated successfully");
    }


    //Approver approve application
    @PutMapping("/approverApprove/{aId}")
    public ResponseEntity<Void> approverApprove(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.approverApprove(aId);
        return ResponseEntity.ok().build();
    }

    //archive application based on application Id.
    @PutMapping("/archive/{applicationUuid}")
    public ResponseEntity<Void> archiveApplication(
            @PathVariable(name = "applicationUuid") final UUID applicationUuid) {
        applicationService.archive(applicationUuid);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationDTO>>  getApplicationByStatus(
            @PathVariable(name = "status") final String status) {
        return ResponseEntity.ok(applicationService.getByStatus(status));
    }


}
