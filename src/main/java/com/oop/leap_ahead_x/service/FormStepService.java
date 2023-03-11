package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.FormStep;
import com.oop.leap_ahead_x.domain.FormWorkflow;
import com.oop.leap_ahead_x.domain.SubformCanvas;
import com.oop.leap_ahead_x.dto.FormStepDTO;
import com.oop.leap_ahead_x.repos.FormStepRepository;
import com.oop.leap_ahead_x.repos.FormWorkflowRepository;
import com.oop.leap_ahead_x.repos.SubformCanvasRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class FormStepService {

    private final FormStepRepository formStepRepository;
    private final FormWorkflowRepository formWorkflowRepository;
    private final SubformCanvasRepository subformCanvasRepository;

    public FormStepService(final FormStepRepository formStepRepository,
            final FormWorkflowRepository formWorkflowRepository,
            final SubformCanvasRepository subformCanvasRepository) {
        this.formStepRepository = formStepRepository;
        this.formWorkflowRepository = formWorkflowRepository;
        this.subformCanvasRepository = subformCanvasRepository;
    }

    public List<FormStepDTO> findAll() {
        final List<FormStep> formSteps = formStepRepository.findAll(Sort.by("stepUuid"));
        return formSteps.stream()
                .map((formStep) -> mapToDTO(formStep, new FormStepDTO()))
                .toList();
    }

    public FormStepDTO get(final UUID stepUuid) {
        return formStepRepository.findById(stepUuid)
                .map(formStep -> mapToDTO(formStep, new FormStepDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final FormStepDTO formStepDTO) {
        final FormStep formStep = new FormStep();
        mapToEntity(formStepDTO, formStep);
        return formStepRepository.save(formStep).getStepUuid();
    }

    public void update(final UUID stepUuid, final FormStepDTO formStepDTO) {
        final FormStep formStep = formStepRepository.findById(stepUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(formStepDTO, formStep);
        formStepRepository.save(formStep);
    }

    public void delete(final UUID stepUuid) {
        formStepRepository.deleteById(stepUuid);
    }

    private FormStepDTO mapToDTO(final FormStep formStep, final FormStepDTO formStepDTO) {
        formStepDTO.setStepUuid(formStep.getStepUuid());
        formStepDTO.setAssigneeType(formStep.getAssigneeType());
        formStepDTO.setOrderNo(formStep.getOrderNo());
        formStepDTO.setAction(formStep.getAction());
        formStepDTO.setParentForm(formStep.getParentForm() == null ? null : formStep.getParentForm().getFormUuid());
        formStepDTO.setAssociatedSubformSubformCanvass(formStep.getAssociatedSubformSubformCanvass() == null ? null : formStep.getAssociatedSubformSubformCanvass().stream()
                .map(subformCanvas -> subformCanvas.getCanvasUuid())
                .toList());
        return formStepDTO;
    }

    private FormStep mapToEntity(final FormStepDTO formStepDTO, final FormStep formStep) {
        formStep.setAssigneeType(formStepDTO.getAssigneeType());
        formStep.setOrderNo(formStepDTO.getOrderNo());
        formStep.setAction(formStepDTO.getAction());
        final FormWorkflow parentForm = formStepDTO.getParentForm() == null ? null : formWorkflowRepository.findById(formStepDTO.getParentForm())
                .orElseThrow(() -> new NotFoundException("parentForm not found"));
        formStep.setParentForm(parentForm);
        final List<SubformCanvas> associatedSubformSubformCanvass = subformCanvasRepository.findAllById(
                formStepDTO.getAssociatedSubformSubformCanvass() == null ? Collections.emptyList() : formStepDTO.getAssociatedSubformSubformCanvass());
        if (associatedSubformSubformCanvass.size() != (formStepDTO.getAssociatedSubformSubformCanvass() == null ? 0 : formStepDTO.getAssociatedSubformSubformCanvass().size())) {
            throw new NotFoundException("one of associatedSubformSubformCanvass not found");
        }
        formStep.setAssociatedSubformSubformCanvass(associatedSubformSubformCanvass.stream().collect(Collectors.toSet()));
        return formStep;
    }

}
