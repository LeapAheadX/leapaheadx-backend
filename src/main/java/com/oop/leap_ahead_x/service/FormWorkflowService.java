package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.*;
import com.oop.leap_ahead_x.dto.FormWorkflowDTO;
import com.oop.leap_ahead_x.dto.FormWorkflowDTO_Post;
import com.oop.leap_ahead_x.repos.*;
import com.oop.leap_ahead_x.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class FormWorkflowService {

    private final FormWorkflowRepository formWorkflowRepository;
    private final AdminRepository adminRepository;

    public FormWorkflowService(final FormWorkflowRepository formWorkflowRepository,
                               final AdminRepository adminRepository) {
        this.formWorkflowRepository = formWorkflowRepository;
        this.adminRepository = adminRepository;
    }

    // Get all Form workflow
    public List<FormWorkflowDTO> findAll() {
        final List<FormWorkflow> formWorkflows = formWorkflowRepository.findAll(Sort.by("formUuid"));
        return formWorkflows.stream()
                .map((formWorkflow) -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .toList();
    }

    // Get a Form workflow by form ID
    public FormWorkflowDTO get(final UUID formUuid) {
        return formWorkflowRepository.findById(formUuid)
                .map(formWorkflow -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .orElseThrow(NotFoundException::new);
    }

    // Create a Form workflow
    public UUID create(final FormWorkflowDTO_Post formWorkflowDTO_post) {
        final FormWorkflow formWorkflow = new FormWorkflow();
        mapToEntity(formWorkflowDTO_post, formWorkflow);
        return formWorkflowRepository.save(formWorkflow).getFormUuid();
    }

    // Update a Form workflow
    public void update(final UUID formUuid, final FormWorkflowDTO_Post formWorkflowDTO_post) {
        final FormWorkflow formWorkflow = formWorkflowRepository.findById(formUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formWorkflowDTO_post, formWorkflow);
        formWorkflowRepository.save(formWorkflow);
    }

    // Delete a Form workflow
    public void delete(final UUID formUuid) {
        formWorkflowRepository.deleteById(formUuid);
    }

    // Mapping Form workflow domain to Data Transfer Object used for Get query
    private FormWorkflowDTO mapToDTO(final FormWorkflow formWorkflow,
                                     final FormWorkflowDTO formWorkflowDTO) {
        formWorkflowDTO.setFormUuid(formWorkflow.getFormUuid());
        formWorkflowDTO.setName(formWorkflow.getName());
        formWorkflowDTO.setDescription(formWorkflow.getDescription());
        formWorkflowDTO.setCreatedBy(formWorkflow.getCreatedBy() == null ? null : formWorkflow.getCreatedBy().getAdminUuid());
        OffsetDateTime dateTime = formWorkflow.getDateCreated();
        // create a formatter to format the date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // format the date string
        String formattedDateTime = formatter.format(dateTime);
        formWorkflowDTO.setDateCreated(formattedDateTime);
        formWorkflowDTO.setArchive(formWorkflow.getArchive());
        return formWorkflowDTO;
    }

    // Mapping Data Transfer Object to From workflow domain used in Post query
    private FormWorkflow mapToEntity(final FormWorkflowDTO_Post formWorkflowDTO_post,
                                     final FormWorkflow formWorkflow) {
        formWorkflow.setName(formWorkflowDTO_post.getName());
        formWorkflow.setDescription(formWorkflowDTO_post.getDescription());
        final Admin createdBy = formWorkflowDTO_post.getCreatedBy() == null ? null : adminRepository.findById(formWorkflowDTO_post.getCreatedBy())
                .orElseThrow(() -> new NotFoundException("createdBy not found"));
        formWorkflow.setCreatedBy(createdBy);
        formWorkflow.setArchive(formWorkflowDTO_post.isArchive());
        return formWorkflow;
    }
}
