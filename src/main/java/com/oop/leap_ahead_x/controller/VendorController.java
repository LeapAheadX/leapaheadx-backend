package com.oop.leap_ahead_x.controller;


import com.oop.leap_ahead_x.dto.VendorDTO;
import com.oop.leap_ahead_x.service.VendorService;
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
@RequestMapping(value = "/api/vendors", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendorController {

    private final VendorService vendorService;

    public VendorController(final VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return ResponseEntity.ok(vendorService.findAll());
    }

    @GetMapping("/{vendorUuid}")
    public ResponseEntity<VendorDTO> getVendor(
            @PathVariable(name = "vendorUuid") final UUID vendorUuid) {
        return ResponseEntity.ok(vendorService.get(vendorUuid));
    }

    @GetMapping("find/{uId}")
    public ResponseEntity<VendorDTO> getVendorByUID(
            @PathVariable(name = "uId") final UUID uId) {
        return ResponseEntity.ok(vendorService.getVendorByUID(uId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createVendor(@RequestBody @Valid final VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.create(vendorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{vendorUuid}")
    public ResponseEntity<Void> updateVendor(
            @PathVariable(name = "vendorUuid") final UUID vendorUuid,
            @RequestBody @Valid final VendorDTO vendorDTO) {
        vendorService.update(vendorUuid, vendorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{vendorUuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVendor(
            @PathVariable(name = "vendorUuid") final UUID vendorUuid) {
        vendorService.delete(vendorUuid);
        return ResponseEntity.noContent().build();
    }

}
