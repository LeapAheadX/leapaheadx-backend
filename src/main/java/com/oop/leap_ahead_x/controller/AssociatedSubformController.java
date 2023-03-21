package com.oop.leap_ahead_x.controller;

import com.oop.leap_ahead_x.dto.AssociatedSubformDTO;
import com.oop.leap_ahead_x.service.AssociatedSubformService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
