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

    //Get an array of subcanvas id which needs to be filled up by the assigned person for a particular application. returns the form Id which is used to render the form
    //http://localhost:8080/api/applications/79ec03aa-bd58-11ed-afa1-0242ac120002/user/79ebaad6-bd58-11ed-afa1-0242ac120002
    @GetMapping("/user/{uId}")
    public ResponseEntity<String> getAssignedApplication(
            @PathVariable(name = "uId") final UUID uId) {
        return applicationService.getAssignedApplication(uId);
    }

    //Get an array of applicationID,formID,formName,status,currentstepNumber based on the vendorID
    //http://localhost:8080/api/applications/vendor/79ebb4e0-bd58-11ed-afa1-0242ac120002
    @GetMapping("/vendor/{vId}")
    public ResponseEntity<String> getApplicationByVendor(
            @PathVariable(name = "vId") final UUID vId) {
        return applicationService.getApplicationByVendor(vId);
    }

    //Get an array of applicationID,formID,formName,status,currentstepNumber based on the vendorID
    //http://localhost:8080/api/applications/getFullForm/79ec053a-bd58-11ed-afa1-0242ac120002
    @GetMapping("/getFullForm/{applicationUuid}")
    public ResponseEntity<String> getApplicationWithAllDetails(
            @PathVariable(name = "applicationUuid") final UUID aId) {
        return applicationService.getApplicationWithAllDetails(aId);
    }

    //Vendor save application
    @PutMapping("/vendorSave/{aId}")
    public ResponseEntity<Void> saveVendor(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.saveVendor(aId);
        return ResponseEntity.ok().build();
    }

    //Vendor submit application
    @PutMapping("/vendorSubmit/{aId}")
    public ResponseEntity<Void> vendorSubmit(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.vendorSubmit(aId);
        return ResponseEntity.ok().build();
    }

    //Admin reject application
    @PutMapping("/adminReject/{aId}")
    public ResponseEntity<Void> adminReject(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.adminReject(aId);
        return ResponseEntity.ok().build();
    }

    //Admin submit application
    @PutMapping("/adminSubmit/{aId}")
    public ResponseEntity<Void> adminSubmit(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.adminSubmit(aId);
        return ResponseEntity.ok().build();
    }

    //Approver reject application
    @PutMapping("/approverReject/{aId}")
    public ResponseEntity<Void> approverReject(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.approverReject(aId);
        return ResponseEntity.ok().build();
    }

    //Approver approve application
    @PutMapping("/approverApprove/{aId}")
    public ResponseEntity<Void> approverApprove(
            @PathVariable(name = "aId") final UUID aId) {
        applicationService.approverApprove(aId);
        return ResponseEntity.ok().build();
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

}
