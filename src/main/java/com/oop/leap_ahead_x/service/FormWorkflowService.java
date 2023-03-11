package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.Admin;
import com.oop.leap_ahead_x.domain.FormWorkflow;
import com.oop.leap_ahead_x.dto.FormWorkflowDTO;
import com.oop.leap_ahead_x.repos.AdminRepository;
import com.oop.leap_ahead_x.repos.FormWorkflowRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FormWorkflowService {

    private final FormWorkflowRepository formWorkflowRepository;
    private final AdminRepository adminRepository;

    public FormWorkflowService(final FormWorkflowRepository formWorkflowRepository,
            final AdminRepository adminRepository) {
        this.formWorkflowRepository = formWorkflowRepository;
        this.adminRepository = adminRepository;
    }

    public List<FormWorkflowDTO> findAll() {
        final List<FormWorkflow> formWorkflows = formWorkflowRepository.findAll(Sort.by("formUuid"));
        return formWorkflows.stream()
                .map((formWorkflow) -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .toList();
    }

    public FormWorkflowDTO get(final UUID formUuid) {
        return formWorkflowRepository.findById(formUuid)
                .map(formWorkflow -> mapToDTO(formWorkflow, new FormWorkflowDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final FormWorkflowDTO formWorkflowDTO) {
        final FormWorkflow formWorkflow = new FormWorkflow();
        mapToEntity(formWorkflowDTO, formWorkflow);
        return formWorkflowRepository.save(formWorkflow).getFormUuid();
    }

    public void update(final UUID formUuid, final FormWorkflowDTO formWorkflowDTO) {
        final FormWorkflow formWorkflow = formWorkflowRepository.findById(formUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formWorkflowDTO, formWorkflow);
        formWorkflowRepository.save(formWorkflow);
    }

    public void delete(final UUID formUuid) {
        formWorkflowRepository.deleteById(formUuid);
    }

    private FormWorkflowDTO mapToDTO(final FormWorkflow formWorkflow,
            final FormWorkflowDTO formWorkflowDTO) {
        formWorkflowDTO.setFormUuid(formWorkflow.getFormUuid());
        formWorkflowDTO.setName(formWorkflow.getName());
        formWorkflowDTO.setDescription(formWorkflow.getDescription());
        formWorkflowDTO.setCreatedBy(formWorkflow.getCreatedBy() == null ? null : formWorkflow.getCreatedBy().getAdminUuid());
        return formWorkflowDTO;
    }

    private FormWorkflow mapToEntity(final FormWorkflowDTO formWorkflowDTO,
            final FormWorkflow formWorkflow) {
        formWorkflow.setName(formWorkflowDTO.getName());
        formWorkflow.setDescription(formWorkflowDTO.getDescription());
        final Admin createdBy = formWorkflowDTO.getCreatedBy() == null ? null : adminRepository.findById(formWorkflowDTO.getCreatedBy())
                .orElseThrow(() -> new NotFoundException("createdBy not found"));
        formWorkflow.setCreatedBy(createdBy);
        return formWorkflow;
    }

    public boolean nameExists(final String name) {
        return formWorkflowRepository.existsByNameIgnoreCase(name);
    }

}
